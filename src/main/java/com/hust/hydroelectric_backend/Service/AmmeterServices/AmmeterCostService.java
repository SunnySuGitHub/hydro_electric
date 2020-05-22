package com.hust.hydroelectric_backend.Service.AmmeterServices;

import com.hust.hydroelectric_backend.Dao.AmmeterCostMapper;
import com.hust.hydroelectric_backend.utils.result.PageQuery;
import com.hust.hydroelectric_backend.utils.result.PageUtils;
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

    public ResultData getAmmeterDailyCost(String meterNo, String enprNo, int readType, int pageNum, int pageSize){
        PageUtils.startPage(new PageQuery(pageNum, pageSize));
        switch (readType) {
            case 0: return Result.success(ammeterCostMapper.getAmmeterTotalDailyCost(meterNo, enprNo));
            case 1: return Result.success(ammeterCostMapper.getAmmeterSharpDailyCost(meterNo, enprNo));
            case 2: return Result.success(ammeterCostMapper.getAmmeterPeekDailyCost(meterNo, enprNo));
            case 3: return Result.success(ammeterCostMapper.getAmmeterFlatDailyCost(meterNo, enprNo));
            case 4: return Result.success(ammeterCostMapper.getAmmeterLowDailyCost(meterNo, enprNo));
            default: return Result.success(null);
        }
    }

}
