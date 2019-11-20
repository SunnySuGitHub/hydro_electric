package com.hust.hydroelectric_backend.Controller;

import com.hust.hydroelectric_backend.Entity.Enpr;
import com.hust.hydroelectric_backend.Service.EnprService;
import com.hust.hydroelectric_backend.utils.ResponseHandler;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/20 14:55
 */
@RestController
public class EnprController {

    @Autowired
    EnprService enprService;

    /**
     * 超级管理员才有权限添加水司
     * 添加公司同时创建一个公司管理员
     */
    @PostMapping("/enpr")
    public ResultData addEnpr(@RequestBody Enpr enpr){
        System.out.println(enpr);
        return ResponseHandler.doHandle(() -> enprService.addEnpr(enpr));
    }

    @GetMapping("enprList")
    public ResultData list(){
        return ResponseHandler.doHandle(() -> enprService.findAll());
    }
    
}
