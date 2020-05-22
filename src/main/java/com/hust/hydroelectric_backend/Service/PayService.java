package com.hust.hydroelectric_backend.Service;

import com.hust.hydroelectric_backend.Dao.PayhistoryMapper;
import com.hust.hydroelectric_backend.Dao.UserMapper;
import com.hust.hydroelectric_backend.Entity.Payhistory;
import com.hust.hydroelectric_backend.Entity.User;
import com.hust.hydroelectric_backend.utils.Constants;
import com.hust.hydroelectric_backend.utils.result.PageQuery;
import com.hust.hydroelectric_backend.utils.result.PageUtils;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/27 16:06
 */
@Service
public class PayService {

    @Resource
    PayhistoryMapper payhistoryMapper;

    @Resource
    UserMapper userMapper;

    @Transactional
    public ResultData paySave(Payhistory payhistory) {
        payhistory.setPayTime(System.currentTimeMillis()/1000);
        payhistory.setPayStatus(Constants.PAY_FAILED);
        int id = payhistoryMapper.save(payhistory);
        User user = userMapper.findByUid(payhistory.getuId());
        user.setAccountBalance(user.getAccountBalance().add(payhistory.getPayAmount()));
        if(userMapper.uptUser(user) == 1) {
            Payhistory justNow = payhistoryMapper.findById(id);
            justNow.setPayStatus(Constants.PAY_SUCCEES);
            return Result.success(payhistoryMapper.update(justNow));
        } else {
            return Result.success("支付失败");
        }
    }

    public ResultData getPayHistory(String enprNo, long startLine, long endLine, int pageNum, int pageSize) {
        PageUtils.startPage(new PageQuery(pageNum, pageSize));
        return Result.success(payhistoryMapper.getPayHistory(enprNo, startLine, endLine));
    }

    public ResultData getOperatorPayHistory(int operatorId, long startLine, long endLine, int pageNum, int pageSize) {
        PageUtils.startPage(new PageQuery(pageNum, pageSize));
        return Result.success(payhistoryMapper.getOperatorPayHistory(operatorId, startLine, endLine));
    }

    public ResultData getUserPayHistory(int uId, long startLine, long endLine, int pageNum, int pageSize) {
        PageUtils.startPage(new PageQuery(pageNum, pageSize));
        return Result.success(payhistoryMapper.getUserPayHistory(uId, startLine, endLine));
    }



}
