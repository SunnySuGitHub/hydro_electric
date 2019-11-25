package com.hust.hydroelectric_backend.Service.WatermeterServices;

import com.hust.hydroelectric_backend.Dao.WatermeterUsageMapper;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/25 15:17
 */
@Service
public class WatermeterUsageService {

    @Resource
    WatermeterUsageMapper watermeterUsageMapper;

    public ResultData getWatemeterDailyUsage(String meterNo, String enprNo) {
        return Result.success(watermeterUsageMapper.getWatemeterDailyUsage(meterNo, enprNo));
    }


}
