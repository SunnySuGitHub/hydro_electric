package com.hust.hydroelectric_backend.ScheduledJob;

import com.hust.hydroelectric_backend.utils.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author: suxinyu
 * @DateTme: 2019/12/2 16:38
 */
@Component
public class JedisRefreshSchedule {

    @Autowired
    JedisUtil jedisUtil;

    @Scheduled(cron = "0 30 1 * * ?")
    public void dailyClean() {
        jedisUtil.flushdb();
    }


}
