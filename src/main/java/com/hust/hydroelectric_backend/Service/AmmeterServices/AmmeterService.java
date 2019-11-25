package com.hust.hydroelectric_backend.Service.AmmeterServices;

import com.hust.hydroelectric_backend.Dao.AmmeterMapper;
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

    public ResultData getAmmeterDetail(String ammeterNo, String enprNo){
        return Result.success(ammeterMapper.getAmmeterDetail(ammeterNo, enprNo));
    }

    public ResultData getFailedAmmeters(int cid){
        return Result.success(ammeterMapper.findFailedAmmeters(cid));
    }

    public ResultData getAmmeterByUid(int uid){
        return Result.success(ammeterMapper.findAmmeterByUid(uid));
    }
}
