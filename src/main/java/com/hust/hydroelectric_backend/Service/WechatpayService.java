package com.hust.hydroelectric_backend.Service;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.hust.hydroelectric_backend.Dao.EnprMapper;
import com.hust.hydroelectric_backend.Dao.PayhistoryMapper;
import com.hust.hydroelectric_backend.Dao.UserMapper;
import com.hust.hydroelectric_backend.Entity.Areas.Enpr;
import com.hust.hydroelectric_backend.Entity.Payhistory;
import com.hust.hydroelectric_backend.Entity.User;
import com.hust.hydroelectric_backend.utils.Constants;
import com.hust.hydroelectric_backend.utils.WXConfigUtil;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/27 16:27
 */
@Service
public class WechatpayService {

    @Resource
    EnprMapper enprMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    PayhistoryMapper payhistoryMapper;



    public ResultData doMicroOrder(String authCode, String fee, int uid, String enprNo) {
        Enpr enpr = enprMapper.findByEnprNo(enprNo);
        User user = userMapper.findByUid(uid);
        if(enpr == null || user == null) {
            return Result.error(HttpStatus.BAD_REQUEST, "不存在此用户");
        }
        Payhistory payhistory = new Payhistory();
        payhistory.setuId(uid);
        BigDecimal payAmount = new BigDecimal(fee).divide(new BigDecimal("100"));
        payhistory.setPayAmount(payAmount);
        payhistory.setPayStatus(Constants.PAY_FAILED);
        payhistory.setPayTime(System.currentTimeMillis()/1000);
        payhistory.setPayMethod(1);
        int payId = payhistoryMapper.save(payhistory);
        String out_trade_no = ""+uid+System.currentTimeMillis();
        Map resMap = new HashMap<>();
        try {
            WXConfigUtil config = new WXConfigUtil();
            config.setAPP_ID(enpr.getAppId());
            config.setMCH_ID(enpr.getMchId());
            config.setKEY(enpr.getWxKey());
            WXPay wxpay = new WXPay(config);
            Map<String, String> req = new HashMap<>();
            //生成商户订单号，不可重复
            req.put("appid", config.getAppID());                                            //公众账号ID
            req.put("mch_id", config.getMchID());                                           //商户号
            req.put("nonce_str", WXPayUtil.generateNonceStr());                             //随机字符串
            req.put("body", enprNo+"-"+"charge");                                           //商品名称
            req.put("out_trade_no", out_trade_no);                                          //商户订单号，自己生成
            req.put("total_fee", fee);                                                      //订单金额int 传来的费用,分数形式  默认货币为CNY
            req.put("spbill_create_ip", Constants.SPBILL_CREATE_IP);                        //调用微信支付API的机器IP
            req.put("auth_code", authCode);                                                 //扫码支付授权码，设备读取用户微信中的条码或者二维码信息
            req.put("sign", WXPayUtil.generateSignature(req, config.getKey(),
                    WXPayConstants.SignType.MD5));
            //使用官方API请求预付订单
            Map<String, String> response = wxpay.microPay(req);
            if ("SUCCESS".equals(response.get("return_code"))) {//表示接口调用成功，但不代表交易成功
                resMap.put("appid", response.get("appid"));
                resMap.put("mch_id", response.get("mch_id"));
                resMap.put("nonce_str", response.get("nonce_str"));
                resMap.put("sign", response.get("sign"));
                resMap.put("transactionId", response.get("transaction_id"));                //微信支付订单号
                resMap.put("outTradeNo", response.get("out_trade_no"));
                resMap.put("timeEnd", response.get("time_end"));
                String resultCode = response.get("result_code");
                if(resultCode.equals("SUCCESS")){//表示交易成功
                    Payhistory prePay = payhistoryMapper.findById(payId);
                    prePay.setTransactionId(response.get("transaction_id"));
                    prePay.setPayStatus(Constants.PAY_SUCCEES);
                    BigDecimal now = user.getAccountBalance();
                    user.setAccountBalance(now.add(new BigDecimal(fee).divide(new BigDecimal("100"))));
                    return successProcess(user, prePay, resMap);
                } else {//表示当前交易尚未成功，可能用户付款中或者取消了订单
                    String errCode = response.get("err_code");
                    Map<String, String> res;
                    if(errCode.equals("USERPAYING")){//付款中状态
                        double ts = System.currentTimeMillis()/1000;
                        do{
                            Thread.sleep(5000);
                            Map<String, String> reReq = new HashMap<>();
                            reReq.put("appid", config.getAppID());
                            reReq.put("mch_id", config.getMchID());
                            reReq.put("out_trade_no", out_trade_no);
                            reReq.put("nonce_str", WXPayUtil.generateNonceStr());
                            reReq.put("sign", WXPayUtil.generateSignature(reReq, config.getKey(),
                                    WXPayConstants.SignType.MD5));
                            res = wxpay.orderQuery(reReq);
                            errCode = res.get("trade_state");
                        } while (errCode != null && errCode.equals("USERPAYING") && (System.currentTimeMillis()/1000 - ts) < 30);
                        if(errCode.equals("SUCCESS")){ // 成功
                            Payhistory prePay = payhistoryMapper.findById(payId);
                            prePay.setTransactionId(response.get("transaction_id"));
                            prePay.setPayStatus(Constants.PAY_SUCCEES);
                            BigDecimal now = user.getAccountBalance();
                            user.setAccountBalance(now.add(new BigDecimal(fee).divide(new BigDecimal("100"))));
                            return successProcess(user, prePay, resMap);
                        } else { //  todo 支付超时失败，为保证资金安全需要撤销订单，后续做
                            return Result.error(HttpStatus.INTERNAL_SERVER_ERROR, "支付失败");
                        }
                    } else {  //付款出现问题
                        return Result.error(HttpStatus.INTERNAL_SERVER_ERROR, "支付失败");
                    }
                }
            } else {
                String errMsg = response.get("return_msg");
                return Result.error(HttpStatus.INTERNAL_SERVER_ERROR, errMsg);
            }
        } catch (Exception e) {
            return Result.error(HttpStatus.INTERNAL_SERVER_ERROR, "内部错误");
        }
    }

    @Transactional
    public ResultData successProcess(User user, Payhistory payhistory, Map resMap){
        try{
            userMapper.uptUser(user);
            payhistoryMapper.update(payhistory);
            return Result.success(resMap);
        } catch (Exception e){
            System.err.println("用户编号为"+user.getuId()+"的用户微信支付"+payhistory.getId()+"成功，但余额未更新");
            return Result.error(HttpStatus.INTERNAL_SERVER_ERROR, "微信支付成功，但内部系统出现问题，后台已记录，后续将给您进行处理");
        }
    }
}