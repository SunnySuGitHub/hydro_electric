package com.hust.hydroelectric_backend.Controller;

import com.hust.hydroelectric_backend.Entity.User;
import com.hust.hydroelectric_backend.Service.UserService;
import com.hust.hydroelectric_backend.utils.ResponseHandler;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/18 15:42
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public ResultData getUser(@RequestParam(name = "uid", defaultValue = "-1") int id){
        return ResponseHandler.doHandle(()->userService.findByUserId(id));
    }

    @DeleteMapping("/user")
    public ResultData delUser(@RequestParam(name = "uid", defaultValue = "-1") int id){
        return ResponseHandler.doHandle(()->userService.delUserById(id));
    }

    @PutMapping("/user")
    public ResultData uptUser(@RequestBody User user){
        return ResponseHandler.doHandle(()->userService.uptUser(user));
    }

    /**
     * 根据楼栋获取用户相关信息
     */
    @GetMapping("/GetUserInfoByBlockId")
    public ResultData getUserInfoByBlockId(@RequestParam(value = "bId", defaultValue = "-1") int bId){
        return ResponseHandler.doHandle(() -> userService)
    }

}
