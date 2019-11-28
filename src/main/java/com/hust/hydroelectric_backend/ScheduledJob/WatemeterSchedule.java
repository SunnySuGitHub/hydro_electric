package com.hust.hydroelectric_backend.ScheduledJob;

import com.hust.hydroelectric_backend.Dao.*;
import com.hust.hydroelectric_backend.Entity.User;
import com.hust.hydroelectric_backend.Entity.Watermeters.LadderedWaterprice;
import com.hust.hydroelectric_backend.Entity.Watermeters.Watermeter;
import com.hust.hydroelectric_backend.Entity.Watermeters.WatermeterCost;
import com.hust.hydroelectric_backend.Entity.Watermeters.WatermeterUsage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/20 15:47
 * 每天定时更新表日用量数据并插入到日用量表
 */
@Component
public class WatemeterSchedule {

    @Resource
    WaterMeterMapper meterMapper;

    @Resource
    WatermeterUsageMapper usageMapper;

    @Resource
    WatermeterCostMapper costMapper;

    @Resource
    LadderedWaterpriceMapper ladderedWaterpriceMapper;

    @Resource
    UserMapper userMapper;


    @Scheduled(cron = "0 0 0 1 * ?")
    public void monthlyRefresh(){
        meterMapper.monthlyRefresh();
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void refreshMonthAmount(){
        meterMapper.dailyRefresh();
    }


    @Scheduled(cron = "0 0 1 * * ?")
    public void generateDailyUsage(){
        List<Watermeter> watermeterList = meterMapper.findAll();
        watermeterList.parallelStream().forEach(watermeter -> watermeterProcess(watermeter));
    }

    @Transactional
    public void watermeterProcess(Watermeter watermeter) {
        watermeter.setPreReadValue(watermeter.getReadValue());
        watermeter.setPreReadTime(watermeter.getReadTime());
        watermeter.setState(0);

        WatermeterUsage watermeterUsage = new WatermeterUsage();
        watermeterUsage.setWatermeterNo(watermeter.getMeterNo());
        watermeterUsage.setEndTime(System.currentTimeMillis()/1000);
        watermeterUsage.setEndValue(watermeter.getReadValue());
        watermeterUsage.setEnprNo(watermeter.getEnprNo());

        int uid = watermeter.getuId();
        WatermeterCost watermeterCost = new WatermeterCost();
        watermeterCost.setuId(uid);
        watermeterCost.setCostTime(System.currentTimeMillis()/1000);
        watermeterCost.setWatermeterNo(watermeter.getMeterNo());
        watermeterCost.setEnprNo(watermeter.getEnprNo());
        if(watermeter.getIsUpdate() == 1) {
            BigDecimal dayUsage = watermeter.getReadValue().subtract(watermeter.getPreReadValue());
            dayUsage = dayUsage.compareTo(BigDecimal.ZERO) == -1 ? BigDecimal.ZERO : dayUsage;
            watermeter.setMonthAmount(watermeter.getMonthAmount().add(dayUsage));

            watermeterUsage.setWatermeterUsaga(dayUsage);

            watermeterCost.setWatermeterUsage(dayUsage);
            int waterType = watermeter.getMeterType();
            List<LadderedWaterprice> levels = ladderedWaterpriceMapper.getLevels(watermeter.getEnprNo(), waterType);
            BigDecimal cost = getCost(levels, dayUsage, watermeter.getMonthAmount());
            watermeterCost.setCostMoney(cost);

            User user = userMapper.findByUid(uid);
            user.setAccountBalance(user.getAccountBalance().subtract(cost));
            userMapper.saveUser(user);
        } else {
            if((System.currentTimeMillis()/1000 - watermeter.getReadTime())/24*3600 >= 2){
                watermeter.setState(1);
            }
            watermeterUsage.setWatermeterUsaga(BigDecimal.ZERO);
            watermeterCost.setCostMoney(BigDecimal.ZERO);
            watermeterCost.setWatermeterUsage(BigDecimal.ZERO);
        }
        meterMapper.uptWatermeterValue(watermeter);
        usageMapper.save(watermeterUsage);
        costMapper.save(watermeterCost);
    }

    private BigDecimal getCost(List<LadderedWaterprice> list, BigDecimal dayUsage, BigDecimal monthAmount){
        int levelCnt = list.size();
        BigDecimal[] prices = new BigDecimal[levelCnt];   //阶梯水费单价
        BigDecimal[] ladders = new BigDecimal[levelCnt];  //阶梯水费起始吨数
        for(int i = 0; i < levelCnt; i++){
            prices[i] = list.get(i).getPrice();
            ladders[i] = list.get(i).getLadder();
        }
        BigDecimal cost = BigDecimal.ZERO;
        int curLevel = levelCnt-1;
        for(; curLevel >=0 ;curLevel--){
            if(monthAmount.compareTo(ladders[curLevel]) == 1) break;
        }

        BigDecimal excludeToday = monthAmount.subtract(dayUsage);
        if(curLevel == 0) { //做容错性处理，讲道理不会出现比最低等级还低的水量，建议最低等级设置为0
            cost = dayUsage.multiply(prices[0]);
        } else {
            boolean isLower = excludeToday.compareTo(ladders[curLevel]) == -1;
            if(isLower){
                BigDecimal beyond = monthAmount.subtract(ladders[curLevel]);
                BigDecimal rest = dayUsage.subtract(beyond);
                BigDecimal higher = beyond.multiply(prices[curLevel]);
                BigDecimal lower = rest.multiply(prices[curLevel-1]);
                cost = higher.add(lower);
            } else {
                cost = dayUsage.multiply(prices[curLevel]);
            }
        }
        return cost;
    }

}
