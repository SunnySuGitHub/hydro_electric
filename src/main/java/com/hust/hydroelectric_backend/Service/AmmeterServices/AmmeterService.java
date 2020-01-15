package com.hust.hydroelectric_backend.Service.AmmeterServices;

import com.alibaba.fastjson.JSONArray;
import com.hust.hydroelectric_backend.Dao.AmmeterMapper;
import com.hust.hydroelectric_backend.Entity.Ammeters.Ammeter;
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
public class AmmeterService {

    @Resource
    AmmeterMapper ammeterMapper;

    @Autowired
    JedisUtil jedisUtil;

    public ResultData getAmmeterDetail(String ammeterNo, String enprNo) {
        return Result.success(ammeterMapper.getAmmeterDetail(ammeterNo, enprNo));
    }

    public ResultData deleteMeter(String meterNo, String enprNo) {
        jedisUtil.hDel(enprNo, "Ammeter_all");
        jedisUtil.hDel(enprNo, "Ammeter_success");
        jedisUtil.hDel(enprNo, "Ammeter_failed");
        return Result.success(ammeterMapper.delete(meterNo, enprNo));
    }

    public ResultData refreshMeter(String enprNo) {
        jedisUtil.hDel(enprNo, "Ammeter_all");
        jedisUtil.hDel(enprNo, "Ammeter_success");
        jedisUtil.hDel(enprNo, "Ammeter_failed");
        return Result.success(1);
    }

    public ResultData getAllAmmeterDetail(String enprNo, int state) {
        switch (state) {
            case -1:
                if (jedisUtil.hGet(enprNo, "Ammeter_all") == null) {
                    List<Ammeter> data = ammeterMapper.getAllAmmeterDetail(enprNo, state);
                    jedisUtil.hSet(enprNo, "Ammeter_all", JSONArray.toJSONString(data));
                    return Result.success(data);
                } else {
                    List<Ammeter> data = JSONArray.parseArray(jedisUtil.hGet(enprNo, "Ammeter_all"), Ammeter.class);
                    return Result.success(data);
                }
            case 0:
                if (jedisUtil.hGet(enprNo, "Ammeter_success") == null) {
                    List<Ammeter> data = ammeterMapper.getAllAmmeterDetail(enprNo, state);
                    jedisUtil.hSet(enprNo, "Ammeter_success", JSONArray.toJSONString(data));
                    return Result.success(data);
                } else {
                    List<Ammeter> data = JSONArray.parseArray(jedisUtil.hGet(enprNo, "Ammeter_success"), Ammeter.class);
                    return Result.success(data);
                }
            case 1:
                if (jedisUtil.hGet(enprNo, "Ammeter_failed") == null) {
                    List<Ammeter> data = ammeterMapper.getAllAmmeterDetail(enprNo, state);
                    jedisUtil.hSet(enprNo, "Ammeter_failed", JSONArray.toJSONString(data));
                    return Result.success(data);
                } else {
                    List<Ammeter> data = JSONArray.parseArray(jedisUtil.hGet(enprNo, "Ammeter_failed"), Ammeter.class);
                    return Result.success(data);
                }
            default:
                return Result.success(null);
        }
    }

    public ResultData getAmmeterByUid(int uid) {
        return Result.success(ammeterMapper.findAmmeterByUid(uid));
    }
}
