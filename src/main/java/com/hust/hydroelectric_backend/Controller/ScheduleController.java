package com.hust.hydroelectric_backend.Controller;

import com.hust.hydroelectric_backend.Entity.ScheduleJob;
import com.hust.hydroelectric_backend.ScheduledJob.DynamicSchedule.ScheduleTaskHandler;
import com.hust.hydroelectric_backend.ScheduledJob.DynamicSchedule.ScheduleUtils;
import com.hust.hydroelectric_backend.utils.ResponseHandler;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author: suxinyu
 * @DateTme: 2019/12/16 11:07
 */
@RestController
@RequestMapping(value = "/scheduled")
public class ScheduleController {

    private static Logger logger = LoggerFactory.getLogger(ScheduleController.class);

    @Autowired
    private ScheduleTaskHandler handler;

    /**
     * 添加新的定时任务
     *     centerId   集中器ID
     *     cron       执行时间
     *     path       待执行的方法所在类
     *     method     方法名
     */
    @PostMapping("/add")
    public ResultData addScheduleTask(@RequestBody ScheduleJob scheduleJob) {
        return ResponseHandler.doHandle(() -> handler.addTriggerTask(scheduleJob));
    }

    /**
     * 修改定时任务执行时间
     */
    @PostMapping("/reset")
    public ResultData resetTaskCron(@RequestBody ScheduleJob scheduleJob) {
        return ResponseHandler.doHandle(() -> handler.resetTriggerTask(scheduleJob));
    }

    @DeleteMapping("/del")
    public ResultData delTaskCron(@RequestParam(name = "centerNo") String centerNo,
                                  @RequestParam(name = "enprNo") String enprNo) {
        return ResponseHandler.doHandle(() -> handler.cancelTriggerTask(centerNo, enprNo));
    }

    /**
     * 查询企业下所有的定时任务
     */
    @GetMapping("/schedule/list/{enprNo}")
    public ResultData getSchedules(@PathVariable(name = "enprNo") String enprNo) {
        if (StringUtils.isNotBlank(enprNo)) {
            return ResponseHandler.doHandle(() -> handler.getScheduleJobs(enprNo));
        } else {
            return Result.error(HttpStatus.BAD_REQUEST, "参数缺失");
        }
    }
}
