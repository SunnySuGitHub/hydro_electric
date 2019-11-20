package com.hust.hydroelectric_backend.ScheduledJob;

import com.hust.hydroelectric_backend.Dao.hydro.AmmeterMapper;
import com.hust.hydroelectric_backend.Dao.hydro.DailyUseMapper;
import com.hust.hydroelectric_backend.Dao.hydro.WaterMeterMapper;
import com.hust.hydroelectric_backend.Entity.Ammeter;
import com.hust.hydroelectric_backend.Entity.DailyUse;
import com.hust.hydroelectric_backend.Entity.Watermeter;
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

    private static int TYPE_WATERMETER = 0;
    private static int TYPE_AMMETER = 1;

    @Resource
    AmmeterMapper ammeterMapper;

    @Resource
    WaterMeterMapper waterMeterMapper;

    @Resource
    DailyUseMapper dailyUseMapper;


    @Scheduled(cron = "0 0 0 * * ?")
    public void generateDailyUse(){
        List<Watermeter> watermeterList = waterMeterMapper.findAll();
        watermeterList.parallelStream().forEach(watermeter -> watermeterProcess(watermeter));
        List<Ammeter> ammeterList = ammeterMapper.findAll();
        ammeterList.parallelStream().forEach(ammeter -> ammeterProcess(ammeter));
    }

    private void watermeterProcess(Watermeter watermeter){
        BigDecimal curReadValue = watermeter.getReadValue();
        long curReadTime = watermeter.getReadTime();
        BigDecimal preReadValue = watermeter.getPreReadValue();
        BigDecimal dailyUse = curReadValue.subtract(preReadValue);
        BigDecimal monthAmount = watermeter.getMonthAmount();
        if(dailyUse.compareTo(BigDecimal.ZERO) == -1) {
            watermeter.setDayAmount(BigDecimal.ZERO);
            watermeter.setState(1);
            watermeter.setPreReadValue(preReadValue);
            watermeter.setPreReadTime(curReadTime);
            watermeter.setReadValue(preReadValue);
            watermeter.setReadTime(System.currentTimeMillis()/1000);
        } else {
            watermeter.setDayAmount(dailyUse);
            watermeter.setState(0);
            watermeter.setPreReadValue(curReadValue);
            watermeter.setPreReadTime(curReadTime);
            watermeter.setReadValue(curReadValue);
            watermeter.setReadTime(System.currentTimeMillis()/1000);
            watermeter.setMonthAmount(monthAmount.add(dailyUse));
        }
        waterMeterMapper.uptWatermeterValue(watermeter);
        DailyUse newDailyUse = new DailyUse();
        newDailyUse.setMeterNo(watermeter.getMeterNo());
        newDailyUse.setDailyAmount(dailyUse);
        newDailyUse.setMeterType(TYPE_WATERMETER);
        newDailyUse.setDateline(watermeter.getPreReadTime());
        newDailyUse.setcId(watermeter.getcId());
        newDailyUse.setEnprNo(watermeter.getEnprNo());
        dailyUseMapper.saveDailyUse(newDailyUse);
    }

    private void ammeterProcess(Ammeter ammeter){
        BigDecimal curReadValue = ammeter.getReadValue();
        long curReadTime = ammeter.getReadTime();
        BigDecimal preReadValue = ammeter.getPreReadValue();
        BigDecimal dailyUse = curReadValue.subtract(preReadValue);
        BigDecimal monthAmount = ammeter.getMonthAmount();
        if(dailyUse.compareTo(BigDecimal.ZERO) == -1) {
            ammeter.setDayAmount(BigDecimal.ZERO);
            ammeter.setState(1);
            ammeter.setPreReadValue(preReadValue);
            ammeter.setPreReadTime(curReadTime);
            ammeter.setReadValue(preReadValue);
            ammeter.setReadTime(System.currentTimeMillis()/1000);
        } else {
            ammeter.setDayAmount(dailyUse);
            ammeter.setState(0);
            ammeter.setPreReadValue(curReadValue);
            ammeter.setPreReadTime(curReadTime);
            ammeter.setReadValue(curReadValue);
            ammeter.setReadTime(System.currentTimeMillis()/1000);
            ammeter.setMonthAmount(monthAmount.add(dailyUse));
        }
        ammeterMapper.uptAmmeterValue(ammeter);
        DailyUse newDailyUse = new DailyUse();
        newDailyUse.setMeterNo(ammeter.getAmmeterNo());
        newDailyUse.setDailyAmount(dailyUse);
        newDailyUse.setMeterType(TYPE_AMMETER);
        newDailyUse.setDateline(ammeter.getPreReadTime());
        newDailyUse.setcId(ammeter.getcId());
        newDailyUse.setEnprNo(ammeter.getEnprNo());
        dailyUseMapper.saveDailyUse(newDailyUse);
    }






}
