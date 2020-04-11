package com.hust.hydroelectric_backend.ScheduledJob.DynamicSchedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.SchedulingException;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * @author: suxinyu
 * @DateTme: 2019/12/16 11:04
 */
@Configuration
@EnableScheduling
public class ScheduleTaskHandler implements SchedulingConfigurer {

    private static Logger logger = LoggerFactory.getLogger(ScheduleTaskHandler.class);

    private ScheduledTaskRegistrar taskRegistrar;
    private Map<String, ScheduledFuture<?>> taskFutureMap = new ConcurrentHashMap<>();

    /**
     * SchedulingConfigurer接口需要实现的方法，初始化任务注册中心
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        this.taskRegistrar = scheduledTaskRegistrar;
    }

    /**
     * 添加任务
     *
     */

    /**
     * 获取future结合，future代表定时任务加到注册中心的一个载体，利用future来操作定时任务
     */
    public void addTriggerTask(String centerId, TriggerTask triggerTask) {
        if (hasTask(centerId)) {
            String message = "the taskId[" + centerId + "] exists";
            logger.error(message);
            throw new SchedulingException(message);
        }

        TaskScheduler scheduler = taskRegistrar.getScheduler();
        //被scheduler调度，只要不被scheduler调度就不会执行
        ScheduledFuture<?> future = scheduler.schedule(triggerTask.getRunnable(), triggerTask.getTrigger());
        try {
            taskFutureMap.put(centerId, future);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 取消任务
     */
    public void cancelTriggerTask(String taskId) {
        ScheduledFuture<?> future = taskFutureMap.get(taskId);
        if (future != null) {
            future.cancel(true);
        } else {
            logger.error("no task for {}", taskId);
            throw new RuntimeException();
        }
        taskFutureMap.remove(taskId);
    }

    /**
     * 重置任务
     */
    public void resetTriggerTask(String taskId, TriggerTask triggerTask) {
        cancelTriggerTask(taskId);
        addTriggerTask(taskId, triggerTask);
    }



    /**
     * 任务编号
     */
    public Set<String> taskIds() {
        return taskFutureMap.keySet();
    }

    /**
     * 是否存在任务
     */
    public boolean hasTask(String taskId) {
        return this.taskFutureMap.containsKey(taskId);
    }
}
