package com.hust.hydroelectric_backend.Controller;

import com.alibaba.fastjson.JSONObject;
import com.hust.hydroelectric_backend.Dao.WechatpayService;
import com.hust.hydroelectric_backend.Entity.Payhistory;
import com.hust.hydroelectric_backend.Entity.User;
import com.hust.hydroelectric_backend.Service.PayService;
import com.hust.hydroelectric_backend.Service.UserService;
import com.hust.hydroelectric_backend.utils.ResponseHandler;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/18 15:42
 * 用户相关操作
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PayService payService;

    @Autowired
    WechatpayService wechatpayService;

    @GetMapping("/user")
    public ResultData getUser(@RequestParam(name = "uid", defaultValue = "-1") int id){
        return ResponseHandler.doHandle(()->userService.findByUserId(id));
    }

    @DeleteMapping("/user")
    public ResultData delUser(@RequestParam(name = "uid", defaultValue = "-1") int id){
        return ResponseHandler.doHandle(()->userService.delUserById(id));
    }

    @PutMapping("/user")
    public ResultData uptUser(@RequestBody User user){
        return ResponseHandler.doHandle(()->userService.uptUser(user));
    }

    /**
     * 根据楼栋获取用户相关信息
     */
    @GetMapping("/GetUserInfoByBlockId")
    public ResultData getUserInfoByBlockId(@RequestParam(value = "bid", defaultValue = "-1") int bId){
        return ResponseHandler.doHandle(() -> userService.getUserInfoByBlockId(bId));
    }

    /**
     * 获取单个用户相关信息
     */
    @GetMapping("/GetUserInfoByUid")
    public ResultData getUserInfoByUid(@RequestParam(value = "uid", defaultValue = "-1") int uid){
        return ResponseHandler.doHandle(() -> userService.getUserInfoByUid(uid));
    }

    /**
     * 查看用户的水费、电费每天扣费记录
     */
    @GetMapping("/user/dailyCost")
    public ResultData userDailyCost(@RequestParam("uid") int uid) {
        return ResponseHandler.doHandle(() -> userService.userDailyCost(uid));
    }

    /**
     * 用户缴费
     */
    @PostMapping("/user/pay")
    public ResultData userPay(@RequestBody Payhistory payhistory) {
        return ResponseHandler.doHandle(() -> payService.paySave(payhistory));
    }

    /**
     * 微信缴费
     */
    @PostMapping("/wechat/pay")
    public ResultData wechatPay(@RequestBody String msg){
        JSONObject jsonObject = JSONObject.parseObject(msg);
        String authcode = jsonObject.getString("authCode");
        String fee = jsonObject.getString("fee");
        String enprNo = jsonObject.getString("fee");
        int uid = jsonObject.getInteger("enprNo");
        return ResponseHandler.doHandle(() -> wechatpayService.doMicroOrder(authcode, fee, uid, enprNo));
    }

}
