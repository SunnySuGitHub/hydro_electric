package com.hust.hydroelectric_backend.Controller;

import com.hust.hydroelectric_backend.Entity.Repair;
import com.hust.hydroelectric_backend.Entity.User;
import com.hust.hydroelectric_backend.Service.RepairService;
import com.hust.hydroelectric_backend.Service.UserService;
import com.hust.hydroelectric_backend.utils.ResponseHandler;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/18 15:42
 * 用户相关操作
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RepairService repairService;

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
    public ResultData getUserInfoByBlockId(@RequestParam(value = "bid", defaultValue = "-1") int bId){
        return ResponseHandler.doHandle(() -> userService.getUserInfoByBlockId(bId));
    }

    /**
     * 获取单个用户相关信息
     */
    @GetMapping("/GetUserInfoByUid")
    public ResultData getUserInfoByUid(@RequestParam(value = "uid", defaultValue = "-1") int uid){
        return ResponseHandler.doHandle(() -> userService.getUserInfoByUid(uid));
    }

    /**
     * 用户报修
     */
    @PostMapping("/repair")
    public ResultData repair(@RequestBody Repair repair){
        return ResponseHandler.doHandle(() -> repairService.add(repair));
    }

    /**
     * 查看小区报修单
     */
    @GetMapping("/repair/list")
    public ResultData repair(@RequestParam("cid") int cid,
                             @RequestParam(value = "state", defaultValue = "-1") int state){
        return ResponseHandler.doHandle(() -> repairService.list(cid, state));
    }

    /**
     * 进度更新
     */
    @PutMapping("/repair")
    public ResultData upt(@RequestBody Repair repair){
        return ResponseHandler.doHandle(() -> repairService.upt(repair));
    }
}
