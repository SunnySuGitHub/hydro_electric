package com.hust.hydroelectric_backend.Service;

import com.hust.hydroelectric_backend.Dao.CommonMeterMapper;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/19 9:32
 */
@Service
public class CommonMeterService {

    @Resource
    CommonMeterMapper commonMeterMapper;

    public ResultData getRunningCnt(int cId){
        return Result.success(commonMeterMapper.getRunningCnt(cId));
    }


}
