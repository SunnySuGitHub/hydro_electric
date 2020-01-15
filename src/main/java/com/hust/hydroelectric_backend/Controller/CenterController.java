package com.hust.hydroelectric_backend.Controller;

import com.hust.hydroelectric_backend.Entity.Center;
import com.hust.hydroelectric_backend.Service.CenterService;
import com.hust.hydroelectric_backend.utils.ResponseHandler;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: suxinyu
 * @DateTme: 2019/12/11 15:16
 * 集中器相关
 */
@RestController
@CrossOrigin("*")
public class CenterController {

    @Autowired
    CenterService centerService;

    /**
     * 添加集中器
     */
    @PostMapping("/center")
    public ResultData addCenter(@RequestBody Center center){
        return ResponseHandler.doHandle(() -> centerService.addCenter(center));
    }

    /**
     * 删除集中器
     */
    @DeleteMapping("/center")
    public ResultData delCenter(@RequestParam("id") int id){
        return ResponseHandler.doHandle(() -> centerService.delCenter(id));
    }

    /**
     * 获取公司下所有集中器
     */
    @GetMapping("/GetCenterByEnprNo")
    public ResultData getCenterByEnprNo(@RequestParam("enprNo") String enprNo){
        return ResponseHandler.doHandle(() -> centerService.getCenterByEnprNo(enprNo));
    }

    /**
     * 获取小区下所有集中器
     */
    @GetMapping("/GetCenterByCid")
    public ResultData getCenterByCid(@RequestParam("cId") int cId){
        return ResponseHandler.doHandle(() -> centerService.getCenterByCid(cId));
    }

    /**
     * 根据集中器获取下面所有水表
     */
    @GetMapping("/GetWatermeterByCenter")
    public ResultData getWatermeterByCenter(@RequestParam("centerId") int centerId){
        return ResponseHandler.doHandle(() -> centerService.getWatermeterByCenter(centerId));
    }

    /**
     * 根据集中器获取下面所有电表
     */
    @GetMapping("/GetAmmeterByCenter")
    public ResultData getAmmeterByCenter(@RequestParam("centerId") int centerId){
        return ResponseHandler.doHandle(() -> centerService.getAmmeterByCenter(centerId));
    }

}
