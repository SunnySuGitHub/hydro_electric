package com.hust.hydroelectric_backend.Controller;

import com.hust.hydroelectric_backend.Entity.Areas.Enpr;
import com.hust.hydroelectric_backend.Entity.BlackList;
import com.hust.hydroelectric_backend.Service.AreaServices.EnprService;
import com.hust.hydroelectric_backend.Service.BlackService;
import com.hust.hydroelectric_backend.utils.ResponseHandler;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

/**
 * @author: suxinyu
 * @DateTme: 2019/12/12 22:36
 * 超级管理员才有的权限
 */
@RestController
@CrossOrigin("*")
public class AdminController {

    @Autowired
    EnprService enprService;

    @Autowired
    BlackService blackService;

    /**
     * 增加公司
     */
    @PostMapping("/enpr")
    public ResultData addEnpr(@RequestBody Enpr enpr){
        System.out.println(enpr);
        return ResponseHandler.doHandle(() -> enprService.addEnpr(enpr));
    }

    /**
     * 获取所有公司
     */
    @GetMapping("/enpr/list")
    public ResultData list(){
        return ResponseHandler.doHandle(() -> enprService.findAll());
    }

    /**
     * 获取管理员的黑名单信息
     */
    @GetMapping("/blacklist/operator/{operatorId}")
    public ResultData getOperatorBlacklist(@PathVariable("operatorId") int operatorId){
        return ResponseHandler.doHandle(() -> blackService.getOperator(operatorId));
    }

    /**
     * 修改黑名单信息
     */
    @PutMapping("/blacklist/oprator")
    public ResultData setOperatorBlacklist(@RequestBody BlackList blackList){
        return ResponseHandler.doHandle(() -> blackService.setOperatorBlackList(blackList));
    }
}
