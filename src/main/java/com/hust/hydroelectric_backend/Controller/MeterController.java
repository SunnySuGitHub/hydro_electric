package com.hust.hydroelectric_backend.Controller;

import com.hust.hydroelectric_backend.Service.AmmeterServices.*;
import com.hust.hydroelectric_backend.Service.CommonMeterService;
import com.hust.hydroelectric_backend.Service.WatermeterServices.*;
import com.hust.hydroelectric_backend.utils.Constants;
import com.hust.hydroelectric_backend.utils.ResponseHandler;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


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
     * 删除表
     * 0为水表  1为电表
     */
    @DeleteMapping("/meter")
    public ResultData delMeter(@RequestParam("enprNo") String enprNo,
                               @RequestParam("meterNo") String meterNo,
                               @RequestParam("meterType") int meterType) {
        if (meterType == Constants.TYPE_WATERMETER) {
            return ResponseHandler.doHandle(() -> waterMeterService.deleteMeter(meterNo, enprNo));
        } else {
            return ResponseHandler.doHandle(() -> ammeterService.deleteMeter(meterNo, enprNo));
        }
    }

    /**
     * 更新表记数据
     * 0为水表  1为电表
     */
    @GetMapping("/refreshMeter")
    public ResultData refreshMeter(@RequestParam("enprNo") String enprNo,
                                   @RequestParam("meterType") int meterType) {
        if (meterType == Constants.TYPE_WATERMETER) {
            return ResponseHandler.doHandle(() -> waterMeterService.refreshMeter(enprNo));
        } else {
            return ResponseHandler.doHandle(() -> ammeterService.refreshMeter(enprNo));
        }
    }


    /**
     * 运行设备统计
     */
    @GetMapping("/RunningCnt")
    public ResultData getRunningCnt(@RequestParam("enprNo") String enprNo) {
        return ResponseHandler.doHandle(() -> commonMeterService.getRunningCnt(enprNo));
    }

    /**
     * 小区表计时间段内使用总量统计
     * 0为水表  1为电表
     */
    @GetMapping("/GetCommunityUsage")
    public ResultData getCommunityUsage(@RequestParam("cId") int cId,
                                        @RequestParam(value = "meterType", defaultValue = "1") int meterType,
                                        @RequestParam("startDateLine") long startLine,
                                        @RequestParam("endDateLine") long endLine,
                                        @RequestParam("enprNo") String enprNo) {
        if (meterType == Constants.TYPE_WATERMETER) {
            return ResponseHandler.doHandle(() -> watermeterUsageService.getCommunityUsage(cId, startLine, endLine, enprNo));
        } else {
            return ResponseHandler.doHandle(() -> ammeterUsageService.getCommunityUsage(cId, startLine, endLine, enprNo));
        }
    }

    /**
     * 根据表编号和公司编码查看表计信息
     * 0为水表  1为电表
     */
    @GetMapping("/GetMeterDetailByMeterNoAndEnprNo")
    public ResultData getMeterDetail(@RequestParam("meterNo") String meterNo,
                                     @RequestParam("enprNo") String enprNo,
                                     @RequestParam(value = "meterType", defaultValue = "1") int meterType) {
        if (StringUtils.isNotBlank(meterNo) && StringUtils.isNotBlank(enprNo)) {
            if (meterType == Constants.TYPE_WATERMETER) {
                return ResponseHandler.doHandle(() -> waterMeterService.getWaterMeterDetail(meterNo, enprNo));
            } else {
                return ResponseHandler.doHandle(() -> ammeterService.getAmmeterDetail(meterNo, enprNo));
            }
        } else {
            return Result.error(HttpStatus.BAD_REQUEST, "参数缺失");
        }

    }

    /**
     * 根据公司编码查看各种状态所有表计信息
     * 0为水表  1为电表
     */
    @GetMapping("/GetAllMeterDetailByEnprNoAndState")
    public ResultData getMeterDetail(@RequestParam("enprNo") String enprNo,
                                     @RequestParam(value = "meterType", defaultValue = "1") int meterType,
                                     @RequestParam(value = "state", defaultValue = "-1") int state) {
        if (StringUtils.isNotBlank(enprNo)) {
            if (meterType == Constants.TYPE_WATERMETER) {
                return ResponseHandler.doHandle(() -> waterMeterService.getAllWaterMeterDetail(enprNo, state));
            } else {
                return ResponseHandler.doHandle(() -> ammeterService.getAllAmmeterDetail(enprNo, state));
            }
        } else {
            return Result.error(HttpStatus.BAD_REQUEST, "参数缺失");
        }

    }

    /**
     * 根据用户id查询特定表信息
     * 0为水表  1为电表
     */
    @GetMapping("/GetMeterDetailByUid")
    public ResultData getMeterDetailByUsernameAndEnprNo(@RequestParam(value = "uId", defaultValue = "1") int uid,
                                                        @RequestParam(value = "meterType", defaultValue = "1") int meterType) {
        if (meterType == Constants.TYPE_WATERMETER) {
            return ResponseHandler.doHandle(() -> waterMeterService.getWatermeterByUid(uid));
        } else {
            return ResponseHandler.doHandle(() -> ammeterService.getAmmeterByUid(uid));
        }
    }

    /**
     * 查询单个水表历史读数记录
     */
    @GetMapping("/GetWatermeterHistorydata")
    public ResultData getWatermeterHistorydata(@RequestParam("meterNo") String meterNo,
                                               @RequestParam("enprNo") String enprNo,
                                               @RequestParam(value = "startDateLine", defaultValue = "-1") long startDateLine,
                                               @RequestParam(value = "endDateLine", defaultValue = "-1") long endDateLine) {

        if (StringUtils.isNotBlank(meterNo) && StringUtils.isNotBlank(enprNo)) {
            return ResponseHandler.doHandle(() -> watermeterHistorydataService.getWatermeterHistorydata(meterNo, enprNo, startDateLine, endDateLine));
        } else {
            return Result.error(HttpStatus.BAD_REQUEST, "参数错误");
        }
    }

    /**
     * 查询单个电表 各类型 历史读数记录
     * readType:
     * 0：总量
     * 1：尖峰
     * 2：峰
     * 3：平
     * 4：谷
     */
    @GetMapping("/GetAmmeterHistorydata")
    public ResultData getAmmeterHistorydata(@RequestParam("meterNo") String meterNo,
                                            @RequestParam("enprNo") String enprNo,
                                            @RequestParam("readType") int readType,
                                            @RequestParam(value = "startDateLine", defaultValue = "-1") long startDateLine,
                                            @RequestParam(value = "endDateLine", defaultValue = "-1") long endDateLine) {
        if (StringUtils.isNotBlank(meterNo) && StringUtils.isNotBlank(enprNo)) {
            return ResponseHandler.doHandle(() -> ammeterHistoryService.getAmmeterHistorydata(meterNo, enprNo, readType, startDateLine, endDateLine));
        } else {
            return Result.error(HttpStatus.BAD_REQUEST, "参数错误");
        }


    }

    /**
     * 查询水表每日 日用量记录
     */
    @GetMapping("/GetWatermeterDailyUsage")
    public ResultData getWatermeterDailyUsage(@RequestParam("meterNo") String meterNo,
                                              @RequestParam("enprNo") String enprNo) {
        if (StringUtils.isNotBlank(meterNo) && StringUtils.isNotBlank(enprNo)) {
            return ResponseHandler.doHandle(() -> watermeterUsageService.getWatemeterDailyUsage(meterNo, enprNo));
        } else {
            return Result.error(HttpStatus.BAD_REQUEST, "参数缺失");
        }
    }

    /**
     * 查询电表 各类型每日 日用量记录
     * readType:
     * 0：总量
     * 1：尖峰
     * 2：峰
     * 3：平
     * 4：谷
     */
    @GetMapping("/GetAmmeterDailyUsage")
    public ResultData getAmmeterDailyUsage(@RequestParam("meterNo") String meterNo,
                                           @RequestParam("enprNo") String enprNo,
                                           @RequestParam("readType") int readType) {
        if (StringUtils.isNotBlank(meterNo) && StringUtils.isNotBlank(enprNo)) {
            return ResponseHandler.doHandle(() -> ammeterUsageService.getAmmeterDailyUsage(meterNo, enprNo, readType));
        } else {
            return Result.error(HttpStatus.BAD_REQUEST, "参数缺失");
        }
    }

    /**
     * 查询水表每日扣费记录
     */
    @GetMapping("/GetWatermeterDailyCost")
    public ResultData getWatermeterDailyCost(@RequestParam("meterNo") String meterNo,
                                             @RequestParam("enprNo") String enprNo) {
        if (StringUtils.isNotBlank(meterNo) && StringUtils.isNotBlank(enprNo)) {
            return ResponseHandler.doHandle(() -> watermeterCostService.getWatermeterDailyCost(meterNo, enprNo));
        } else {
            return Result.error(HttpStatus.BAD_REQUEST, "参数缺失");
        }
    }

    /**
     * 查询电表 各类型 每日扣费记录
     * 0：总量
     * 1：尖峰
     * 2：峰
     * 3：平
     * 4：谷
     */
    @GetMapping("/GetAmmeterDailyCost")
    public ResultData getAmmeterDailyCost(@RequestParam("ammeterNo") String ammeterNo,
                                           @RequestParam("enprNo") String enprNo,
                                          @RequestParam("readType") int readType) {
        if (StringUtils.isNotBlank(ammeterNo) && StringUtils.isNotBlank(enprNo)) {
            return ResponseHandler.doHandle(() -> ammeterCostService.getAmmeterDailyCost(ammeterNo, enprNo, readType));
        } else {
            return Result.error(HttpStatus.BAD_REQUEST, "参数缺失");
        }
    }



}
