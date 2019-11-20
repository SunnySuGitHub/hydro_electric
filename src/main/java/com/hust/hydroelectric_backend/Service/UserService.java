package com.hust.hydroelectric_backend.Service;

import com.hust.hydroelectric_backend.Dao.hydro.BlockMapper;
import com.hust.hydroelectric_backend.Dao.hydro.UserMapper;
import com.hust.hydroelectric_backend.Entity.User;
import com.hust.hydroelectric_backend.Entity.UserInfoVo;
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
}
