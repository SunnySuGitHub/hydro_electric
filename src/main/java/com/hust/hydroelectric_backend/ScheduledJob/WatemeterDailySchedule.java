package com.hust.hydroelectric_backend.ScheduledJob;

import com.hust.hydroelectric_backend.Dao.AmmeterMapper;
import com.hust.hydroelectric_backend.Dao.WaterMeterMapper;
import com.hust.hydroelectric_backend.Entity.Watermeters.Watermeter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/20 15:47
 * 每天定时更新表日用量数据并插入到日用量表
 */
@Component
public class WatemeterDailySchedule {

    @Resource
    AmmeterMapper ammeterMapper;

    @Resource
    WaterMeterMapper waterMeterMapper;


    @Scheduled(cron = "0 0 0 * * ?")
    public void generateDailyUsage(){
        List<Watermeter> watermeterList = waterMeterMapper.findAll();
        watermeterList.parallelStream().forEach(watermeter -> watermeterProcess(watermeter));
    }

    private void watermeterProcess(Watermeter watermeter) {

    }

}
