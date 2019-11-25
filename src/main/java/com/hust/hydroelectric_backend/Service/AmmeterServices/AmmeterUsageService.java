package com.hust.hydroelectric_backend.Service.AmmeterServices;

import com.hust.hydroelectric_backend.Dao.AmmeterUsageMapper;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/23 19:05
 */
@Service
public class AmmeterUsageService {

    @Resource
    AmmeterUsageMapper ammeterUsageMapper;

    public ResultData getAmmeterDailyUsage(String ammeterNo, String enprNo){
        return Result.success(ammeterUsageMapper.getAmmeterDailyUsage(ammeterNo, enprNo));
    }

    public ResultData getAmmeterUsageDetail(String ammeterNo, String enprNo, long startLine, long endLine) {
        return Result.success(ammeterUsageMapper.getAmmeterUsageDetail(ammeterNo, enprNo, startLine, endLine));
    }

    public ResultData getCommunityUsage(int cid, long startLine, long endLine, String enprNo) {
        return Result.success(ammeterUsageMapper.getCommunityUsage(cid, startLine, endLine, enprNo));
    }
}
