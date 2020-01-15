package com.hust.hydroelectric_backend.Service.AmmeterServices;

import com.hust.hydroelectric_backend.Dao.AmmeterRateMapper;
import com.hust.hydroelectric_backend.Entity.Ammeters.AmmeterRate;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: suxinyu
 * @DateTme: 2019/12/11 15:05
 */
@Service
public class RateService {

    @Resource
    AmmeterRateMapper rateMapper;

    public ResultData getRateList(String enprNo){
        return Result.success(rateMapper.getRateList(enprNo));
    }

    public ResultData uptRate(AmmeterRate ammeterRate) {
        return Result.success(rateMapper.updateRate(ammeterRate));
    }


}
