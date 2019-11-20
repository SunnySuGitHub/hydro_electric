package com.hust.hydroelectric_backend.Controller;

import com.hust.hydroelectric_backend.Service.AmmeterService;
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

    @Autowired
    AmmeterService ammeterService;

    /**
     * 运行设备统计
     */
    @GetMapping("/RunningCnt")
    public ResultData getRunningCnt(@RequestParam("cid") Integer cId){
        return ResponseHandler.doHandle(() -> deviceService.getRunningCnt(cId));
    }

    /**
     * 水表近日使用量展示
     */
    @GetMapping("/GetWateMeterDailyUseDetail")
    public ResultData getWateMeterDailyUseDetail(@RequestParam("cid") Integer cId,
                                                 @RequestParam("startDateLine") Long startDateLine,
                                                 @RequestParam("endDateLine") Long endDateLine){
        return ResponseHandler.doHandle(() -> waterMeterService.getWateMeterDailyUseDetail(cId, startDateLine, endDateLine));
    }

    /**
     * 电表近日使用量展示
     */
    @GetMapping("/GetAmmeterDailyUseDetail")
    public ResultData getAmmeterDailyUseDetail(@RequestParam(value = "cid", defaultValue = "-1") Integer cid,
                                               @RequestParam("startDateLine") Long startLine,
                                               @RequestParam("endDateLine") Long endLine) {
        return ResponseHandler.doHandle(() -> ammeterService.getAmmeterDailyUseDetail(cid, startLine, endLine));
    }

    /**
     * 水表信息查看
     */
    @GetMapping("GetWateMeterDetail")
    public ResultData getWaterMeterDetail(@RequestParam("meterNo") String meterNo,
                                          @RequestParam("enprNo") String enprNo) {
        return ResponseHandler.doHandle(() -> waterMeterService.getWaterMeterDetail(meterNo, enprNo));
    }

    /**
     * 电表信息查看
     */
    @GetMapping("GetAmmeterDetail")
    public ResultData getAmmeterDetail(@RequestParam("meterNo") String ammeterNo,
                                       @RequestParam("enprNo") String enprNo) {
        return ResponseHandler.doHandle(() -> ammeterService.getAmmeterDetail(ammeterNo, enprNo));
    }



}
