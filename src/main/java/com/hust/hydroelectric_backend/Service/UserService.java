package com.hust.hydroelectric_backend.Service;

import com.hust.hydroelectric_backend.Dao.hydro.UserMapper;
import com.hust.hydroelectric_backend.Entity.User;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
        return Result.success()
    }
}
