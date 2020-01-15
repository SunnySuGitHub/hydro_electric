package com.hust.hydroelectric_backend.ScheduledJob.DynamicSchedule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author: suxinyu
 * @DateTme: 2019/12/16 11:02
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"com.hust.hydroelectric_backend.ScheduledJob.DynamicSchedule"})
public class Config {
    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(10);
        // 线程名前缀
        threadPoolTaskScheduler.setThreadNamePrefix("task-dispatch-");
        threadPoolTaskScheduler.setAwaitTerminationSeconds(600);
        // 当调度器shutdown被调用时等待当前被调度的任务完成
        threadPoolTaskScheduler.setWaitForTasksToCompleteOnShutdown(false);
        // 设置当任务被取消的同时从当前调度器移除的策略
        threadPoolTaskScheduler.setRemoveOnCancelPolicy(true);
        return threadPoolTaskScheduler;
    }
}
