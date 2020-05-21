package com.hust.hydroelectric_backend.Service;

import com.hust.hydroelectric_backend.Dao.BlackListMapper;
import com.hust.hydroelectric_backend.Entity.BlackList;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author: suxinyu
 * @DateTme: 2019/12/12 22:42
 */
@Service
public class BlackService {

    @Resource
    BlackListMapper blackListMapper;

    public ResultData getOperator(int operatorId) {
        return Result.success(blackListMapper.getOperator(operatorId));
    }

    public ResultData setOperatorBlackList(BlackList blackList) {
        return Result.success(blackListMapper.setOperatorBlackList(blackList));
    }
}
