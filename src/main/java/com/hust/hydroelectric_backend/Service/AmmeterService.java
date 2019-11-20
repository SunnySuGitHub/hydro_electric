package com.hust.hydroelectric_backend.Service;

import com.hust.hydroelectric_backend.Dao.hydro.AmmeterMapper;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/19 20:05
 */
@Service
public class AmmeterService {

    @Resource
    AmmeterMapper ammeterMapper;

    public ResultData getAmmeterDailyUseDetail(int cId, long startLine, long endLine){
        return Result.success(ammeterMapper.getDailyUseDetails(cId, startLine, endLine));
    }

    public ResultData getAmmeterDetail(String ammeterNo, String enprNo){
        return Result.success(ammeterMapper.getAmmeterDetail(ammeterNo, enprNo));
    }
}
