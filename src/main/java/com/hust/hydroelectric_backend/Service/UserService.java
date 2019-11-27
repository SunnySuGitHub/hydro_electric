package com.hust.hydroelectric_backend.Service;

import com.hust.hydroelectric_backend.Dao.*;
import com.hust.hydroelectric_backend.Entity.Ammeters.Ammeter;
import com.hust.hydroelectric_backend.Entity.User;
import com.hust.hydroelectric_backend.Entity.VO.MeterDailyCost;
import com.hust.hydroelectric_backend.Entity.VO.UserInfoVo;
import com.hust.hydroelectric_backend.Entity.Watermeters.Watermeter;
import com.hust.hydroelectric_backend.Entity.Watermeters.WatermeterCost;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
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

    public ResultData findByUserId(int uid){
        return Result.success(userMapper.findByUid(uid));
    }

    public ResultData delUserById(int uid){
        return Result.success(userMapper.delUserByUid(uid));
    }

    public ResultData uptUser(User user){
        return Result.success(userMapper.uptUser(user));
    }

    public ResultData getUserInfoByBlockId(int bId){
        List<Integer> uids = userMapper.findUidsByBid(bId);
        List<UserInfoVo> res = new ArrayList<>();
        for(int uid : uids){
            res.addAll(userMapper.findUserInfoVoByUid(uid));
        }
        return Result.success(res);
    }

    public ResultData getUserInfoByUid(int uid){
        return Result.success(userMapper.findUserInfoVoByUid(uid));
    }

    public ResultData userDailyCost(int uid) {
        List<Watermeter> meters = waterMeterMapper.findWatermeterByUid(uid);
        List<Ammeter> ammeters = ammeterMapper.findAmmeterByUid(uid);
        List<MeterDailyCost> personalCost = new ArrayList<>();
        for(Watermeter watermeter : meters) {
            personalCost.addAll(watermeterCostMapper.getWatermeterDailyCost(watermeter.getMeterNo(), watermeter.getEnprNo()));
        }
        for(Ammeter ammeter : ammeters) {
            personalCost.addAll(ammeterCostMapper.getAmmeterDailyCost(ammeter.getAmmeterNo(), ammeter.getEnprNo()));
        }
        return Result.success(personalCost);
    }
}
