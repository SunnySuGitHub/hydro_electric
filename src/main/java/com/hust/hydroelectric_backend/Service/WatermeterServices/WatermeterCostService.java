package com.hust.hydroelectric_backend.Service.WatermeterServices;

import com.hust.hydroelectric_backend.Dao.WatermeterCostMapper;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/25 15:44
 */
@Service
public class WatermeterCostService {

    @Resource
    WatermeterCostMapper watermeterCostMapper;

    public ResultData getWatermeterDailyCost(String meterNo, String enprNo){
        return Result.success(watermeterCostMapper.getWatermeterDailyCost(meterNo, enprNo));
    }



}
