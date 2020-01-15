package com.hust.hydroelectric_backend.Service;

import com.hust.hydroelectric_backend.Dao.AmmeterMapper;
import com.hust.hydroelectric_backend.Dao.CenterMapper;
import com.hust.hydroelectric_backend.Dao.WaterMeterMapper;
import com.hust.hydroelectric_backend.Entity.Center;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: suxinyu
 * @DateTme: 2019/12/11 15:21
 */
@Service
public class CenterService {

    @Resource
    CenterMapper centerMapper;

    @Resource
    WaterMeterMapper waterMeterMapper;

    @Resource
    AmmeterMapper ammeterMapper;

    public ResultData addCenter(Center center) {
        center.setCreateTime(System.currentTimeMillis()/1000);
        return Result.success(centerMapper.save(center));
    }

    public ResultData delCenter(int id){
        return Result.success(centerMapper.delete(id));
    }

    public ResultData getCenterByEnprNo(String enprNo) {
        return Result.success(centerMapper.getCenterByEnprNo(enprNo));
    }

    public ResultData getCenterByCid(int cId) {
        return Result.success(centerMapper.getCenterByCid(cId));
    }

    public ResultData getWatermeterByCenter(int centerId) {
        return Result.success(waterMeterMapper.getWatermeterByCenter(centerId));
    }

    public ResultData getAmmeterByCenter(int centerId) {
        return Result.success(ammeterMapper.getAmmeterByCenter(centerId));
    }


}
