package com.hust.hydroelectric_backend.Controller;

import com.hust.hydroelectric_backend.Service.DeviceService;
import com.hust.hydroelectric_backend.Service.WaterMeterService;
import com.hust.hydroelectric_backend.utils.ResponseHandler;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/19 9:29
 */
@RestController
public class MeterController {

    @Autowired
    DeviceService deviceService;

    @Autowired
    WaterMeterService waterMeterService;

    /**
     * 运行设备统计
     */
    @GetMapping("/RunningCnt")
    public ResultData getRunningCnt(@RequestParam("cId") Integer cId){
        return ResponseHandler.doHandle(() -> deviceService.getRunningCnt(cId));
    }

    /**
     * 水表近日使用量展示
     */
    @GetMapping("/GetWateMeterDailyUseDetail")
    public ResultData getWateMeterDailyUseDetail(@RequestParam("cId") Integer cId,
                                                 @RequestParam("startDateLine") Long startDateLine,
                                                 @RequestParam("endDateLine") Long endDateLine){
        return ResponseHandler.doHandle(() -> waterMeterService.getWateMeterDailyUseDetail(cId, startDateLine, endDateLine));
    }



}
