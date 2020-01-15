package com.hust.hydroelectric_backend.ScheduledJob.DynamicSchedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;

import java.lang.reflect.Method;

/**
 * @author: suxinyu
 * @DateTme: 2019/12/16 11:06
 */
public class ScheduleUtils {

    private static Logger logger = LoggerFactory.getLogger(ScheduleUtils.class);

    /**
     * 根据方法反射和cron构建TriggerTask
     */
    public static TriggerTask getTriggerTask(String methodName, String cron) throws Exception{
        TriggerTask triggerTask = new TriggerTask(() -> {
            try {
                Class clazz = Class.forName("com.hust.hydroelectric_backend.ScheduledJob.DynamicSchedule.Jobs");
                Object object = clazz.newInstance();
                Method method = clazz.getMethod(methodName);
                method.invoke(object,null);
            }catch (Exception e){
                logger.error(e.getMessage());
            }
        }, (TriggerContext triggerContext) -> {
            CronTrigger trigger=new CronTrigger(cron);
            return trigger.nextExecutionTime(triggerContext);
        });
        return triggerTask;
    }
}
