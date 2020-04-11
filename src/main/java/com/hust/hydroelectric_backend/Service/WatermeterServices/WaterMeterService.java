package com.hust.hydroelectric_backend.Service.WatermeterServices;

import com.alibaba.fastjson.JSONArray;
import com.hust.hydroelectric_backend.Dao.WaterMeterMapper;
import com.hust.hydroelectric_backend.Entity.Watermeters.Watermeter;
import com.hust.hydroelectric_backend.utils.JedisUtil;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/19 20:05
 */
@Service
public class WaterMeterService {

    @Resource
    WaterMeterMapper waterMeterMapper;

    @Autowired
    JedisUtil jedisUtil;

    public ResultData getWaterMeterDetail(String meterNo, String enprNo) {
        return Result.success(waterMeterMapper.getWaterMeterDetail(meterNo, enprNo));
    }

    public ResultData deleteMeter(String meterNo, String enprNo) {
        jedisUtil.hDel(enprNo, "Watetermeter_all");
        jedisUtil.hDel(enprNo, "Watermeter_success");
        jedisUtil.hDel(enprNo, "Watermeter_failed");
        return Result.success(waterMeterMapper.delete(meterNo, enprNo));
    }

    public ResultData refreshMeter(String enprNo) {
        jedisUtil.hDel(enprNo, "Watetermeter_all");
        jedisUtil.hDel(enprNo, "Watermeter_success");
        jedisUtil.hDel(enprNo, "Watermeter_failed");
        return Result.success(1);
    }

    public ResultData getAllWaterMeterDetail(String enprNo, int state) {
        switch (state) {
            case -1:
//                if (jedisUtil.hGet(enprNo, "Watetermeter_all") == null) {
//                    List<Watermeter> data = waterMeterMapper.getAllWaterMeterDetail(enprNo, state);
//                    jedisUtil.hSet(enprNo, "Watetermeter_all", JSONArray.toJSONString(data));
//                    return Result.success(data);
//                } else {
//                    List<Watermeter> data = JSONArray.parseArray(jedisUtil.hGet(enprNo, "Watetermeter_all"), Watermeter.class);
//                    return Result.success(data);
//                }
                return Result.success(waterMeterMapper.getAllWaterMeterDetail(enprNo, state));
            case 0:
                if (jedisUtil.hGet(enprNo, "Watermeter_success") == null) {
                    List<Watermeter> data = waterMeterMapper.getAllWaterMeterDetail(enprNo, state);
                    jedisUtil.hSet(enprNo, "Watermeter_success", JSONArray.toJSONString(data));
                    return Result.success(data);
                } else {
                    List<Watermeter> data = JSONArray.parseArray(jedisUtil.hGet(enprNo, "Watermeter_success"), Watermeter.class);
                    return Result.success(data);
                }
            case 1:
                if (jedisUtil.hGet(enprNo, "Watermeter_failed") == null) {
                    List<Watermeter> data = waterMeterMapper.getAllWaterMeterDetail(enprNo, state);
                    jedisUtil.hSet(enprNo, "Watermeter_failed", JSONArray.toJSONString(data));
                    return Result.success(data);
                } else {
                    List<Watermeter> data = JSONArray.parseArray(jedisUtil.hGet(enprNo, "Watermeter_faileld"), Watermeter.class);
                    return Result.success(data);
                }
            default:
                return Result.success(null);
        }
    }

    public ResultData getWatermeterByUid(int uid) {
        return Result.success(waterMeterMapper.findWatermeterByUid(uid));
    }
}
