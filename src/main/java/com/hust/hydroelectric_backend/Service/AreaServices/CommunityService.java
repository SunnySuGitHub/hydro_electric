package com.hust.hydroelectric_backend.Service.AreaServices;

import com.hust.hydroelectric_backend.Dao.hydro.CommunityMapper;
import com.hust.hydroelectric_backend.Entity.Areas.Community;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/19 16:33
 */
@Service
public class CommunityService {

    @Resource
    CommunityMapper communityMapper;

    public ResultData getCommunity(int id){
        if(id == -1){
            return Result.error(HttpStatus.BAD_REQUEST, "参数不规范");
        } else {
            return Result.success(communityMapper.getCommunity(id));
        }
    }

    public ResultData addCommunity(Community community){
        return Result.success(communityMapper.addCommunity(community));
    }

    public ResultData uptCommunity(Community community){
        return Result.success(communityMapper.uptCommunity(community));
    }

    public ResultData delCommunity(int id){
        if(id == -1){
            return Result.error(HttpStatus.BAD_REQUEST, "参数不规范");
        } else {
            int res = communityMapper.delCommunity(id);
            if(res == 1) return Result.success("删除成功");
            return Result.error(HttpStatus.INTERNAL_SERVER_ERROR, "删除失败");

        }
    }

}
