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
    @GetMapping("/GetCenterByEnprNo/{enprNo}")
    public ResultData getCenterByEnprNo(@PathVariable("enprNo") String enprNo,
                                        @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return ResponseHandler.doHandle(() -> centerService.getCenterByEnprNo(enprNo,pageNum, pageSize));
    }

    /**
     * 获取小区下所有集中器
     */
    @GetMapping("/GetCenterByCid/{cId}")
    public ResultData getCenterByCid(@PathVariable("cId") int cId){
        return ResponseHandler.doHandle(() -> centerService.getCenterByCid(cId));
    }

    /**
     * 根据集中器获取下面所有水表
     */
    @GetMapping("/GetWatermeterByCenter/{centerId}")
    public ResultData getWatermeterByCenter(@PathVariable("centerId") int centerId,
                                            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return ResponseHandler.doHandle(() -> centerService.getWatermeterByCenter(centerId, pageNum, pageSize));
    }

    /**
     * 根据集中器获取下面所有电表
     */
    @GetMapping("/GetAmmeterByCenter/{centerId}")
    public ResultData getAmmeterByCenter(@PathVariable("centerId") int centerId,
                                         @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return ResponseHandler.doHandle(() -> centerService.getAmmeterByCenter(centerId, pageNum, pageSize));
    }

}
