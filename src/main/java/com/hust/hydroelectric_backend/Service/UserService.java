package com.hust.hydroelectric_backend.Service;

import com.github.pagehelper.Page;
import com.hust.hydroelectric_backend.Dao.*;
import com.hust.hydroelectric_backend.Entity.Ammeters.Ammeter;
import com.hust.hydroelectric_backend.Entity.User;
import com.hust.hydroelectric_backend.Entity.VO.MeterDailyCost;
import com.hust.hydroelectric_backend.Entity.VO.UserInfoVo;
import com.hust.hydroelectric_backend.Entity.Watermeters.Watermeter;
import com.hust.hydroelectric_backend.utils.Constants;
import com.hust.hydroelectric_backend.utils.result.PageQuery;
import com.hust.hydroelectric_backend.utils.result.PageUtils;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    public ResultData findByUserId(int uid, String enprNo) {
        User user = userMapper.findByUid(uid);
        return Result.success(user);
    }

    public ResultData delUserById(int uid, String enprNo) {
        return Result.success(userMapper.delUserByUid(uid));
    }

    public ResultData uptUser(User user) {
        String enprNo = user.getEnprNo();
        return Result.success(userMapper.uptUser(user));
    }

    public ResultData test() {
        List<UserInfoVo> res = userMapper.test();
        return Result.success(res);
    }

    public ResultData getUserInfoByBlockId(int bId, int pageNum, int pageSize) {
        List<Integer> uids = userMapper.findUidsByBid(bId);
        PageUtils.startPage(new PageQuery(pageNum, pageSize));
        return Result.success(userMapper.findUserInfoVoByUids(uids));
    }

    public ResultData getUserInfoByUid(int uid) {
        List<UserInfoVo> infoVos = userMapper.findUserInfoVoByUid(uid);
        return Result.success(infoVos);
    }

    public ResultData getUserInfoByUname(String uname, String enprNo, int pageNum, int pageSize) {
        List<Integer> uids = userMapper.findUidsByUname(uname, enprNo);
        PageUtils.startPage(new PageQuery(pageNum, pageSize));
        return Result.success(userMapper.findUserInfoVoByUids(uids));
    }

    public ResultData getUserInfoByMeterNo(String meterNo, int meterType, String enprNo) {
        int uid = -1;
        if (meterType == Constants.TYPE_WATERMETER) {
            uid = waterMeterMapper.getWaterMeterDetail(meterNo, enprNo).getuId();
        } else {
            uid = ammeterMapper.getAmmeterDetail(meterNo, enprNo).getuId();
        }
        return Result.success(userMapper.findUserInfoVoByUid(uid));
    }


    public ResultData userDailyCost(int uid, String enprNo) {
        List<Watermeter> meters = waterMeterMapper.findWatermeterByUid(uid);
        List<Ammeter> ammeters = ammeterMapper.findAmmeterByUid(uid);
        List<MeterDailyCost> personalCost = new ArrayList<>();
        for (Watermeter watermeter : meters) {
            personalCost.addAll(watermeterCostMapper.getWatermeterDailyCost(watermeter.getMeterNo(), watermeter.getEnprNo()));
        }
        for (Ammeter ammeter : ammeters) {
            personalCost.addAll(ammeterCostMapper.getAmmeterTotalDailyCost(ammeter.getAmmeterNo(), ammeter.getEnprNo()));
        }
        return Result.success(personalCost);
    }
}
