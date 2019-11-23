package com.hust.hydroelectric_backend.ScheduledJob;

import com.hust.hydroelectric_backend.Dao.hydro.AmmeterMapper;
import com.hust.hydroelectric_backend.Dao.hydro.WaterMeterMapper;
import com.hust.hydroelectric_backend.Entity.Watermeters.Watermeter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/20 15:47
 * 每天定时更新表日用量数据并插入到日用量表
 */
@Component
public class DailyUseSchedule {

    @Resource
    AmmeterMapper ammeterMapper;

    @Resource
    WaterMeterMapper waterMeterMapper;


    @Scheduled(cron = "0 0 0 * * ?")
    public void generateDailyUse(){
        List<Watermeter> watermeterList = waterMeterMapper.findAll();
        watermeterList.parallelStream().forEach(watermeter -> watermeterProcess(watermeter));
    }

    private void watermeterProcess(Watermeter watermeter) {
        BigDecimal curReadValue = watermeter.getReadValue();
        long curReadTime = watermeter.getReadTime();
        BigDecimal preReadValue = watermeter.getPreReadValue();
        BigDecimal dailyUse = curReadValue.subtract(preReadValue);
        if(dailyUse.compareTo(BigDecimal.ZERO) == -1) {
            watermeter.setState(1);
            watermeter.setPreReadValue(preReadValue);
            watermeter.setPreReadTime(curReadTime);
            watermeter.setReadValue(preReadValue);
            watermeter.setReadTime(System.currentTimeMillis()/1000);
        } else {
            watermeter.setState(0);
            watermeter.setPreReadValue(curReadValue);
            watermeter.setPreReadTime(curReadTime);
            watermeter.setReadValue(curReadValue);
            watermeter.setReadTime(System.currentTimeMillis()/1000);
        }
        waterMeterMapper.uptWatermeterValue(watermeter);
    }

}
