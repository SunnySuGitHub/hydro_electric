package com.hust.hydroelectric_backend.Controller;

import com.hust.hydroelectric_backend.ScheduledJob.DynamicSchedule.ScheduleTaskHandler;
import com.hust.hydroelectric_backend.ScheduledJob.DynamicSchedule.ScheduleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @param param 参数都存在数据库，到时候从数据库获取
     *        centerId   集中器ID
     *        cron       执行时间
     *        path       待执行的方法所在类
     *        method     方法名
     */
    @PostMapping("/add")
    @ResponseBody
    public void addScheduleTask(@RequestBody Map<String, String> param) {
        String taskId = param.get("centerId");
        String cron = param.get("cron");
        String methodName = param.get("methodName");
        if ("".equals(taskId) || "".equals(cron) || "".equals(methodName)) {
            logger.warn("empty string");
        }
        try {
            TriggerTask triggerTask = ScheduleUtils.getTriggerTask(methodName,cron);
            handler.addTriggerTask(taskId,triggerTask);
        }catch (Exception e){
            logger.error("添加定时任务异常：{}",e);
        }
    }

    /**
     * 修改定时任务执行时间
     */
    @PostMapping("/reset")
    public void resetTaskCron(@RequestBody Map<String, String> param) {
        String taskId = param.get("centerId");
        String cron = param.get("cron");
        String methodName = param.get("methodName");
        if ("".equals(taskId) || "".equals(cron) || "".equals(methodName)) {
            logger.warn("empty string");
        }
        try {
            TriggerTask triggerTask = ScheduleUtils.getTriggerTask(methodName,cron);
            handler.resetTriggerTask(taskId, triggerTask);
        }catch (Exception e){
            logger.error("修改定时任务异常：{}",e);
        }
    }

    @GetMapping("/del")
    public void delTaskCron(@RequestParam(name = "centerId") String taskId) {
        handler.cancelTriggerTask(taskId);
    }
}
