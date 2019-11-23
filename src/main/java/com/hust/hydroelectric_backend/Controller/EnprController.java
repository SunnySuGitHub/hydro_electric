package com.hust.hydroelectric_backend.Controller;

import com.hust.hydroelectric_backend.Entity.Areas.Enpr;
import com.hust.hydroelectric_backend.Service.AreaServices.EnprService;
import com.hust.hydroelectric_backend.utils.ResponseHandler;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/enpr")
    public ResultData getEnprMsg(@RequestParam("enprNo") String enprNo){
        return ResponseHandler.doHandle(() -> enprService.getByEnprNo(enprNo));
    }

    @PutMapping("/enpr")
    public ResultData uptEnprMsg(@RequestParam("enprNo") String enprNo){
        return ResponseHandler.doHandle(() -> enprService.uptByEnprNo(enprNo));
    }

    @GetMapping("/enprList")
    public ResultData list(){
        return ResponseHandler.doHandle(() -> enprService.findAll());
    }

}
