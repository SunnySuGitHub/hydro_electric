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
        for(Community community : communityList) {
            res.add(commonMeterMapper.getRunningCnt(community.getcId()));
        }
        return Result.success(res);
    }


}
