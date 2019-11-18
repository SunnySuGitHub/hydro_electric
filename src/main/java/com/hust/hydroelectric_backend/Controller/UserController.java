package com.hust.hydroelectric_backend.Controller;

import com.hust.hydroelectric_backend.Service.UserService;
import com.hust.hydroelectric_backend.utils.ResponseHandler;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/18 15:42
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/list")
    public ResultData getDocList(@RequestParam(name = "uid", defaultValue = "-1") int id){
        return ResponseHandler.doHandle(()->userService.findByUserId(id));
    }
}
