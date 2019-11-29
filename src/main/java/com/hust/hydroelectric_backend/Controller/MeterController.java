package com.hust.hydroelectric_backend.Controller;

import com.hust.hydroelectric_backend.Service.AmmeterServices.AmmeterCostService;
import com.hust.hydroelectric_backend.Service.AmmeterServices.AmmeterHistoryService;
import com.hust.hydroelectric_backend.Service.AmmeterServices.AmmeterService;
import com.hust.hydroelectric_backend.Service.AmmeterServices.AmmeterUsageService;
import com.hust.hydroelectric_backend.Service.CommonMeterService;
import com.hust.hydroelectric_backend.Service.WatermeterServices.WaterMeterService;
import com.hust.hydroelectric_backend.Service.WatermeterServices.WatermeterCostService;
import com.hust.hydroelectric_backend.Service.WatermeterServices.WatermeterHistorydataService;
import com.hust.hydroelectric_backend.Service.WatermeterServices.WatermeterUsageService;
import com.hust.hydroelectric_backend.utils.Constants;
import com.hust.hydroelectric_backend.utils.ResponseHandler;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author: suxinyu
 * @DateTme: 2019/11/19 9:29
 * 表计相关操作
 */
@RestController
@CrossOrigin("*")
public class MeterController {

    @Autowired
    CommonMeterService commonMeterService;

    @Autowired
    AmmeterService ammeterService;

    @Autowired
    AmmeterUsageService ammeterUsageService;

    @Autowired
    AmmeterHistoryService ammeterHistoryService;

    @Autowired
    AmmeterCostService ammeterCostService;

    @Autowired
    WatermeterHistorydataService watermeterHistorydataService;

    @Autowired
    WaterMeterService waterMeterService;

    @Autowired
    WatermeterUsageService watermeterUsageService;

    @Autowired
    WatermeterCostService watermeterCostService;

    /**
     * 运行设备统计
     */
    @GetMapping("/RunningCnt")
    public ResultData getRunningCnt(@RequestParam("cId") int cId){
        return ResponseHandler.doHandle(() -> commonMeterService.getRunningCnt(cId));
    }

    /**
     * 小区表计时间段内使用总量统计
     */
    @GetMapping("/GetCommunityUsage")
    public ResultData getCommunityUsage(@RequestParam("cId") int cId,
                                        @RequestParam(value = "meterType", defaultValue = "1") int meterType,
                                        @RequestParam("startDateLine") long startLine,
                                        @RequestParam("endDateLine") long endLine,
                                        @RequestParam("enprNo") String enprNo){
        if(meterType == Constants.TYPE_WATERMETER) return ResponseHandler.doHandle(() -> watermeterUsageService.getCommunityUsage(cId, startLine, endLine, enprNo));
        return ResponseHandler.doHandle(() -> ammeterUsageService.getCommunityUsage(cId, startLine, endLine, enprNo));
    }

    /**
     * 根据表编号和公司编码查看表计信息
     * 0为水表  1为电表
     */
    @GetMapping("/GetMeterDetailByMeterNoAndEnprNo")
    public ResultData getMeterDetail(@RequestParam("meterNo") String meterNo,
                                     @RequestParam("enprNo") String enprNo,
                                     @RequestParam(value = "meterType", defaultValue = "1") int meterType) {
        if(StringUtils.isNotBlank(meterNo) && StringUtils.isNotBlank(enprNo)) {
            if(meterType == Constants.TYPE_WATERMETER)
                return ResponseHandler.doHandle(() -> waterMeterService.getWaterMeterDetail(meterNo, enprNo));
            return ResponseHandler.doHandle(() -> ammeterService.getAmmeterDetail(meterNo, enprNo));
        } else {
            return Result.error(HttpStatus.BAD_REQUEST, "参数缺失");
        }

    }

    /**
     * 根据用户id查询表信息
     */
    @GetMapping("/GetMeterDetailByUid")
    public ResultData getMeterDetailByUsernameAndEnprNo(@RequestParam(value = "uId", defaultValue = "1") int uid,
                                                        @RequestParam(value = "meterType", defaultValue = "1") int meterType){
        if(meterType == Constants.TYPE_WATERMETER)
            return ResponseHandler.doHandle(() -> waterMeterService.getWatermeterByUid(uid));
        return ResponseHandler.doHandle(() -> ammeterService.getAmmeterByUid(uid));
    }

    /**
     * 查看小区故障表
     * 0为水表  1为电表
     */
    @GetMapping("/GetFailedMeters")
    public ResultData getFailedMeters(@RequestParam(value = "cId", defaultValue = "1") int cid,
                                      @RequestParam(value = "meterType", defaultValue = "1") int meterType){
        if(meterType == Constants.TYPE_WATERMETER)
            return ResponseHandler.doHandle(() -> waterMeterService.getFailedWaterMeters(cid));
        return ResponseHandler.doHandle(() -> ammeterService.getFailedAmmeters(cid));
    }

    /**
     * 查询表计历史用量
     * 0为水表  1为电表
     */
    @GetMapping("/GetMeterHistorydata")
    public ResultData getMeterHistorydata(@RequestParam("meterNo") String meterNo,
                                          @RequestParam("enprNo") String enprNo,
                                          @RequestParam("startDateLine") long startDateLine,
                                          @RequestParam("endDateLine") long endDateLine,
                                          @RequestParam(value = "meterType", defaultValue = "1") Integer meterType){
        if(meterType == Constants.TYPE_WATERMETER)
            return ResponseHandler.doHandle(()-> watermeterHistorydataService.getWatermeterHistorydata(meterNo, enprNo, startDateLine, endDateLine));
        return ResponseHandler.doHandle(()-> ammeterHistoryService.getAmmeterHistorydata(meterNo, enprNo, startDateLine, endDateLine));
    }

    /**
     * 查询表计用量记录 天维度
     * 水表一天一记录 可以方法合并
     */
    @GetMapping("/GetMeterDailyUsage")
    public ResultData getAmmeterDailyUsage(@RequestParam("meterNo") String meterNo,
                                           @RequestParam("enprNo") String enprNo,
                                           @RequestParam(value = "meterType", defaultValue = "1") int meterType){
        if(StringUtils.isNotBlank(meterNo) && StringUtils.isNotBlank(enprNo)) {
            if(meterType == Constants.TYPE_WATERMETER)
                return ResponseHandler.doHandle(() -> watermeterUsageService.getWatemeterDailyUsage(meterNo, enprNo));
            return ResponseHandler.doHandle(() -> ammeterUsageService.getAmmeterDailyUsage(meterNo, enprNo));
        } else {
            return Result.error(HttpStatus.BAD_REQUEST, "参数缺失");
        }
    }

    /**
     * 查询表计扣费记录 天维度
     */
    @GetMapping("/GetAmmeterDailyCost")
    public ResultData getAmmeterDailyCost(@RequestParam("meterNo") String meterNo,
                                          @RequestParam("enprNo") String enprNo,
                                          @RequestParam(value = "meterType", defaultValue = "1") int meterType){
        if(StringUtils.isNotBlank(meterNo) && StringUtils.isNotBlank(enprNo)) {
            if(meterType == Constants.TYPE_WATERMETER)
                return ResponseHandler.doHandle(() -> watermeterCostService.getWatermeterDailyCost(meterNo, enprNo));
            return ResponseHandler.doHandle(() -> ammeterCostService.getAmmeterDailyCost(meterNo, enprNo));
        } else {
            return Result.error(HttpStatus.BAD_REQUEST, "参数缺失");
        }
    }

    /**
     * 查询电表用量记录详情
     * 水表一天一次统计 不存在详情统计，直接调用天维度的用量统计
     */
    @GetMapping("/GetAmmeterUsageDetail")
    public ResultData getAmmeterUsageDetail(@RequestParam("meterNo") String meterNo,
                                            @RequestParam("enprNo") String enprNo,
                                            @RequestParam(value = "startDateline", defaultValue = "0000000000") long startLine,
                                            @RequestParam(value = "endDateline", defaultValue = "9999999999") long endLine){
        if(StringUtils.isNotBlank(meterNo) && StringUtils.isNotBlank(enprNo)) {
            return ResponseHandler.doHandle(() -> ammeterUsageService.getAmmeterUsageDetail(meterNo, enprNo, startLine, endLine));
        } else {
            return Result.error(HttpStatus.BAD_REQUEST, "参数缺失");
        }
    }

    /**
     * 查询电表扣费记录详情
     */
    @GetMapping("/GetAmmeterCostDetail")
    public ResultData getAmmeterCostDetail(@RequestParam("ammeterNo") String ammeterNo,
                                            @RequestParam("enprNo") String enprNo,
                                            @RequestParam(value = "startDateline", defaultValue = "0000000000") long startLine,
                                            @RequestParam(value = "endDateline", defaultValue = "9999999999") long endLine){
        if(StringUtils.isNotBlank(ammeterNo) && StringUtils.isNotBlank(enprNo)) {
            return ResponseHandler.doHandle(() -> ammeterCostService.getAmmeterCostDetail(ammeterNo, enprNo, startLine, endLine));
        } else {
            return Result.error(HttpStatus.BAD_REQUEST, "参数缺失");
        }
    }



}
