package com.hust.hydroelectric_backend.Controller;

import com.hust.hydroelectric_backend.Service.AmmeterService;
import com.hust.hydroelectric_backend.Service.DailyUseService;
import com.hust.hydroelectric_backend.Service.CommonMeterService;
import com.hust.hydroelectric_backend.Service.WaterMeterService;
import com.hust.hydroelectric_backend.utils.Constants;
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
    CommonMeterService commonMeterService;

    @Autowired
    WaterMeterService waterMeterService;

    @Autowired
    AmmeterService ammeterService;

    @Autowired
    DailyUseService dailyUseService;

    /**
     * 运行设备统计
     */
    @GetMapping("/RunningCnt")
    public ResultData getRunningCnt(@RequestParam("cid") Integer cId){
        return ResponseHandler.doHandle(() -> commonMeterService.getRunningCnt(cId));
    }

    /**
     * 表计近日使用量展示
     * 0为水表  1为电表
     */
    @GetMapping("/GetMeterDailyUseDetail")
    public ResultData getWateMeterDailyUseDetail(@RequestParam("cid") Integer cId,
                                                 @RequestParam("startDateLine") Long startDateLine,
                                                 @RequestParam("endDateLine") Long endDateLine,
                                                 @RequestParam(value = "meterType", defaultValue = "1") Integer meterType){
        return ResponseHandler.doHandle(() -> dailyUseService.getDailuUseDatail(cId, startDateLine, endDateLine, meterType));
    }

    /**
     * 根据表编号和公司编码查看表计信息
     * 0为水表  1为电表
     */
    @GetMapping("/GetMeterDetailByMeterNoAndEnprNo")
    public ResultData getMeterDetail(@RequestParam("meterNo") String meterNo,
                                     @RequestParam("enprNo") String enprNo,
                                     @RequestParam(value = "meterType", defaultValue = "1") int meterType) {
        if(meterType == Constants.TYPE_WATERMETER) return ResponseHandler.doHandle(() -> waterMeterService.getWaterMeterDetail(meterNo, enprNo));
        return ResponseHandler.doHandle(() -> ammeterService.getAmmeterDetail(meterNo, enprNo));
    }

    /**
     * 查看小区故障表
     * 0为水表  1为电表
     */
    @GetMapping("/GetFailedMeters")
    public ResultData getFailedMeters(@RequestParam(value = "cid", defaultValue = "1") int cid,
                                      @RequestParam(value = "meterType", defaultValue = "1") int meterType){
        if(meterType == Constants.TYPE_WATERMETER) return ResponseHandler.doHandle(() -> waterMeterService.getFailedWaterMeters(cid));
        return ResponseHandler.doHandle(() -> ammeterService.getFailedAmmeters(cid));
    }

    /**
     * 根据用户编号查询表信息
     */
    @GetMapping("/GetMeterDetailByUsernameAndEnprNo")
    public ResultData getMeterDetailByUsernameAndEnprNo(@RequestParam(value = "userNo", defaultValue = "张三") String uNo,
                                                        @RequestParam(value = "meterType", defaultValue = "1") int meterType,
                                                        @RequestParam("enprNo") String enprNo){
        if(meterType == Constants.TYPE_WATERMETER) return ResponseHandler.doHandle(() -> waterMeterService.getWatermeterByUnoAndEnprNo(uNo, enprNo));
        return ResponseHandler.doHandle(() -> ammeterService.getAmmeterByUnoAndEnprNo(uNo, enprNo));
    }



}
