package com.hust.hydroelectric_backend.Service;

import com.hust.hydroelectric_backend.Dao.CommonMeterMapper;
import com.hust.hydroelectric_backend.Dao.CommunityMapper;
import com.hust.hydroelectric_backend.Entity.Areas.Community;
import com.hust.hydroelectric_backend.Entity.VO.RunningDevice;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/19 9:32
 */
@Service
public class CommonMeterService {

    @Resource
    CommonMeterMapper commonMeterMapper;

    @Resource
    CommunityMapper communityMapper;

    public ResultData getRunningCnt(String enprNo){
        List<Community> communityList = communityMapper.communityList(enprNo);
        List<RunningDevice> res = new ArrayList<>();
        res.add(new RunningDevice(3,"沿江村",75,74,75,73));
        res.add(new RunningDevice(4,"十户长村",243,240,243,242));
        res.add(new RunningDevice(5,"马道坝村",213,212,213,210));
        res.add(new RunningDevice(6,"马鞍山村",67,67,67,67));
//        for(Community community : communityList) {
//            res.add(commonMeterMapper.getRunningCnt(community.getcId()));
//        }
        return Result.success(res);
    }


}
