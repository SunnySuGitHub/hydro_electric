package com.hust.hydroelectric_backend.ScheduledJob.DynamicSchedule;

import com.hust.hydroelectric_backend.Dao.ScheduleMapper;
import com.hust.hydroelectric_backend.Entity.ScheduleJob;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.SchedulingException;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * @author: suxinyu
 * @DateTme: 2019/12/16 11:04
 */
@EnableScheduling
@Service
public class ScheduleTaskHandler implements SchedulingConfigurer {

    private static Logger logger = LoggerFactory.getLogger(ScheduleTaskHandler.class);
    private ScheduledTaskRegistrar taskRegistrar;
    private Map<Integer, ScheduledFuture<?>> taskFutureMap = new ConcurrentHashMap<>();

    @Resource
    ScheduleMapper scheduleMapper;

    //SchedulingConfigurer接口需要实现的方法，初始化任务注册中心
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        this.taskRegistrar = scheduledTaskRegistrar;
    }

    //添加任务。  获取future结合，future代表定时任务加到注册中心的一个载体，利用future来操作定时任务
    public ResultData addTriggerTask(ScheduleJob scheduleJob) throws Exception {
        TriggerTask triggerTask = ScheduleUtils.getTriggerTask(scheduleJob.getJobType(), scheduleJob.getCron());
        Integer centerId = scheduleMapper.getCenterId(scheduleJob.getCenterNo(), scheduleJob.getEnprNo());
        if (centerId != null && hasTask(centerId)) {
            String message = "the taskId[" + centerId + "] exists";
            logger.error(message);
            throw new SchedulingException(message);
        }
        scheduleMapper.addScheduleJob(scheduleJob);
        int taskId = scheduleJob.getJobId();
        TaskScheduler scheduler = taskRegistrar.getScheduler();
        //被scheduler调度，只要不被scheduler调度就不会执行
        ScheduledFuture<?> future = scheduler.schedule(triggerTask.getRunnable(), triggerTask.getTrigger());
        taskFutureMap.put(taskId, future);
        return Result.success(1);
    }

    public ResultData getScheduleJobs(String enprNo) {
        return Result.success(scheduleMapper.getScheduleJobs(enprNo));
    }

    /**
     * 取消任务
     */
    @Transactional
    public ResultData cancelTriggerTask(String centerNo, String enprNo) {
        int centerId = scheduleMapper.getCenterId(centerNo, enprNo);
        ScheduledFuture<?> future = taskFutureMap.get(centerId);
        if (future != null) {
            future.cancel(true);
        } else {
            logger.error("no task for {}", centerId);
            throw new RuntimeException();
        }
        taskFutureMap.remove(centerId);
        return Result.success(scheduleMapper.deleteScheduleJob(centerId));
    }

    /**
     * 修改任务
     */
    public ResultData resetTriggerTask(ScheduleJob scheduleJob) throws Exception {
        cancelTriggerTask(scheduleJob.getCenterNo(), scheduleJob.getEnprNo());
        addTriggerTask(scheduleJob);
        return Result.success(1);
    }


    /**
     * 是否存在任务
     */
    public boolean hasTask(int taskId) {
        return this.taskFutureMap.containsKey(taskId);
    }


}
