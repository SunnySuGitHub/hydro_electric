package com.hust.hydroelectric_backend.Service.WatermeterServices;

import com.hust.hydroelectric_backend.Dao.WaterMeterMapper;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/19 20:05
 */
@Service
public class WaterMeterService {

    @Resource
    WaterMeterMapper waterMeterMapper;

    public ResultData getWaterMeterDetail(String meterNo, String enprNo) {
        return Result.success(waterMeterMapper.getWaterMeterDetail(meterNo, enprNo));
    }

    public ResultData getFailedWaterMeters(int cid){
        return Result.success(waterMeterMapper.findFailedWatermeter(cid));
    }

    public ResultData getWatermeterByUid(int uid){
        return Result.success(waterMeterMapper.findWatermeterByUid(uid));
    }
}
