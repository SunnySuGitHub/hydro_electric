package com.hust.hydroelectric_backend.ScheduledJob;

import com.hust.hydroelectric_backend.Dao.UserMapper;
import com.hust.hydroelectric_backend.Entity.User;
import com.hust.hydroelectric_backend.utils.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/12/2 16:38
 */
@Component
public class JedisRefreshSchedule {

    @Resource
    UserMapper userMapper;

    @Autowired
    JedisUtil jedisUtil;

    @Scheduled(cron = "0 30 1 * * ?")
    public void MeterDailyCostRefresh(){
        List<User> userList = userMapper.findTotalUser();
        for(User user : userList){
            String enprNo = user.getEnprNo();
            int uid = user.getuId();
            jedisUtil.hDel(enprNo, "MeterDailyCost"+uid);
        }
    }




}
