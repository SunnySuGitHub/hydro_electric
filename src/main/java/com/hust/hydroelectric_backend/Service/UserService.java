package com.hust.hydroelectric_backend.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.hust.hydroelectric_backend.Dao.*;
import com.hust.hydroelectric_backend.Entity.Ammeters.Ammeter;
import com.hust.hydroelectric_backend.Entity.User;
import com.hust.hydroelectric_backend.Entity.VO.MeterDailyCost;
import com.hust.hydroelectric_backend.Entity.VO.UserInfoVo;
import com.hust.hydroelectric_backend.Entity.Watermeters.Watermeter;
import com.hust.hydroelectric_backend.utils.Constants;
import com.hust.hydroelectric_backend.utils.JedisUtil;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/18 15:43
 */
@Service
public class UserService {
    @Resource
    UserMapper userMapper;

    @Resource
    WaterMeterMapper waterMeterMapper;

    @Resource
    AmmeterMapper ammeterMapper;

    @Resource
    WatermeterCostMapper watermeterCostMapper;

    @Resource
    AmmeterCostMapper ammeterCostMapper;

    @Autowired
    JedisUtil jedisUtil;

    public ResultData findByUserId(int uid, String enprNo){
        if(jedisUtil.hGet(enprNo, "Uid"+uid) == null) {
            User user = userMapper.findByUid(uid);
            jedisUtil.hSet(enprNo, "Uid"+uid, JSON.toJSONString(user));
            return Result.success(user);
        } else {
            User user = JSON.parseObject(jedisUtil.hGet(enprNo, "Uid"+uid), User.class);
            return Result.success(user);
        }
    }

    public ResultData delUserById(int uid, String enprNo){
        jedisUtil.hDel(enprNo, "Uid"+uid);
        jedisUtil.hDel(enprNo, "UserInfoUid"+uid);
        return Result.success(userMapper.delUserByUid(uid));
    }

    public ResultData uptUser(User user){
        String enprNo = user.getEnprNo();
        jedisUtil.hDel(enprNo, "Uid"+user.getuId());
        jedisUtil.hDel(enprNo, "UserInfoUid"+user.getuId());
        return Result.success(userMapper.uptUser(user));
    }

    public ResultData getUserInfoByBlockId(int bId, String enprNo){
        List<Integer> uids = userMapper.findUidsByBid(bId);
        List<UserInfoVo> res = new ArrayList<>();
        for(int uid : uids){
            if(jedisUtil.hGet(enprNo, "UserInfoUid"+uid) == null) {
                List<UserInfoVo> infoVo = userMapper.findUserInfoVoByUid(uid);
                jedisUtil.hSet(enprNo, "UserInfoUid"+uid, JSONArray.toJSONString(infoVo));
                res.addAll(infoVo);
            } else {
                List<UserInfoVo> infoVo = JSONArray.parseArray(jedisUtil.hGet(enprNo, "UserInfoUid"+uid), UserInfoVo.class);
                res.addAll(infoVo);
            }
        }
        return Result.success(res);
    }

    public ResultData getUserInfoByUid(int uid, String enprNo){
        if(jedisUtil.hGet(enprNo, "UserInfoUid"+uid) == null) {
            List<UserInfoVo> infoVos = userMapper.findUserInfoVoByUid(uid);
            jedisUtil.hSet(enprNo, "UserInfoUid"+uid, JSONArray.toJSONString(infoVos));
            return Result.success(infoVos);
        } else {
            List<UserInfoVo> infoVos = JSONArray.parseArray(jedisUtil.hGet(enprNo, "UserInfoUid"+uid), UserInfoVo.class);
            return Result.success(infoVos);
        }
    }

    public ResultData getUserInfoByUname(String uname, String enprNo){
        List<Integer> uids = userMapper.findUidsByUname(uname, enprNo);
        List<UserInfoVo> res = new ArrayList<>();
        for(int uid : uids) {
            res.addAll(userMapper.findUserInfoVoByUid(uid));
        }
        return Result.success(res);
    }

    public ResultData getUserInfoByMeterNo(String meterNo, int meterType, String enprNo){
        int uid = -1;
        if(meterType == Constants.TYPE_WATERMETER) {
            uid = waterMeterMapper.getWaterMeterDetail(meterNo, enprNo).getuId();
        } else {
            uid = ammeterMapper.getAmmeterDetail(meterNo, enprNo).getuId();
        }
        return Result.success(userMapper.findUserInfoVoByUid(uid));
    }


    public ResultData userDailyCost(int uid, String enprNo) {
        //每天定时一点半清空redis记录
        if(jedisUtil.hGet(enprNo, "MeterDailyCost"+uid) == null) {
            List<Watermeter> meters = waterMeterMapper.findWatermeterByUid(uid);
            List<Ammeter> ammeters = ammeterMapper.findAmmeterByUid(uid);
            List<MeterDailyCost> personalCost = new ArrayList<>();
            for(Watermeter watermeter : meters) {
                personalCost.addAll(watermeterCostMapper.getWatermeterDailyCost(watermeter.getMeterNo(), watermeter.getEnprNo()));
            }
            for(Ammeter ammeter : ammeters) {
                personalCost.addAll(ammeterCostMapper.getAmmeterDailyCost(ammeter.getAmmeterNo(), ammeter.getEnprNo()));
            }
            jedisUtil.hSet(enprNo, "MeterDailyCost"+uid, JSONArray.toJSONString(personalCost));
            return Result.success(personalCost);
        } else {
            List<MeterDailyCost> personalCost = JSONArray.parseArray(
                    jedisUtil.hGet(enprNo, "MeterDailyCost"+uid), MeterDailyCost.class);
            return Result.success(personalCost);
        }
    }
}
