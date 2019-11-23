package com.hust.hydroelectric_backend.Service.AmmeterServices;

import com.hust.hydroelectric_backend.Dao.hydro.AmmeterCostMapper;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/23 23:00
 */
@Service
public class AmmeterCostService {

    @Resource
    AmmeterCostMapper ammeterCostMapper;

    public ResultData getAmmeterDailyCost(String meterNo, String enprNo){
        return Result.success(ammeterCostMapper.getAmmeterDailyCost(meterNo, enprNo));
    }

    public ResultData getAmmeterCostDetail(String meterNo, String enprNo, long startLine, long endLine){
        return Result.success(ammeterCostMapper.getAmmeterCostDetail(meterNo, enprNo, startLine, endLine));
    }



}
