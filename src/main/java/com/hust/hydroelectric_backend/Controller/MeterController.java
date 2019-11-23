package com.hust.hydroelectric_backend.Controller;

import com.hust.hydroelectric_backend.Service.*;
import com.hust.hydroelectric_backend.utils.Constants;
import com.hust.hydroelectric_backend.utils.ResponseHandler;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    AmmeterUsageService ammeterUsageService;

    @Autowired
    WatermeterHistorydataService watermeterHistorydataService;

    @Autowired
    AmmeterHistoryService ammeterHistoryService;

    /**
     * 运行设备统计
     */
    @GetMapping("/RunningCnt")
    public ResultData getRunningCnt(@RequestParam("cid") Integer cId){
        return ResponseHandler.doHandle(() -> commonMeterService.getRunningCnt(cId));
    }

    /**
     * 表计历史用量展示
     * 0为水表  1为电表
     */
    @GetMapping("/GetMeterHistorydata")
    public ResultData getMeterHistorydata(@RequestParam("ammeter_no") String meterNo,
                                          @RequestParam("enprNo") String enprNo,
                                          @RequestParam("startDateLine") Long startDateLine,
                                          @RequestParam("endDateLine") Long endDateLine,
                                          @RequestParam(value = "meterType", defaultValue = "1") Integer meterType){
        if(meterType == Constants.TYPE_WATERMETER)
            return ResponseHandler.doHandle(()-> watermeterHistorydataService.getWatermeterHistorydata(meterNo, enprNo, startDateLine, endDateLine));
        return ResponseHandler.doHandle(()-> ammeterHistoryService.getAmmeterHistorydata(meterNo, enprNo, startDateLine, endDateLine));
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

    /**
     * 查询电表日用量
     */
    @GetMapping("GetAmmeterDailyUsage")
    public ResultData getAmmeterDailyUsage(@RequestParam("ammeter_no") String ammeterNo,
                                           @RequestParam("enprNo") String enprNo){
        if(StringUtils.isNotEmpty(ammeterNo) && StringUtils.isNotEmpty(enprNo)) {
            return ResponseHandler.doHandle(() -> ammeterUsageService.getAmmeterDailyUsage(ammeterNo, enprNo));
        } else {
            return Result.error(HttpStatus.BAD_REQUEST, "参数缺失");
        }
    }

    /**
     * 查询电表用量详情
     */
    @GetMapping("GetAmmeterUsageDetail")
    public ResultData getAmmeterUsageDetail(@RequestParam("ammeter_no") String ammeterNo,
                                            @RequestParam("enprNo") String enprNo,
                                            @RequestParam(value = "startDateline", defaultValue = "0000000000") long startLine,
                                            @RequestParam(value = "endDateline", defaultValue = "9999999999") long endLine){
        if(StringUtils.isNotEmpty(ammeterNo) && StringUtils.isNotEmpty(enprNo)) {
            return ResponseHandler.doHandle(() -> ammeterUsageService.getAmmeterUsageDetail(ammeterNo, enprNo, startLine, endLine));
        } else {
            return Result.error(HttpStatus.BAD_REQUEST, "参数缺失");
        }
    }




}
