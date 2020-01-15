package com.hust.hydroelectric_backend.ScheduledJob;

import com.hust.hydroelectric_backend.Dao.*;
import com.hust.hydroelectric_backend.Entity.Ammeters.*;
import com.hust.hydroelectric_backend.Entity.User;
import com.hust.hydroelectric_backend.utils.TimeUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/12/10 16:09
 * 每日计算电量电费
 */
@Component
public class AmmeterSchedule {

    @Resource
    UserMapper userMapper;

    @Resource
    AmmeterMapper ammeterMapper;

    @Resource
    AmmeterRateMapper rateMapper;

    @Resource
    AmmeterCostMapper costMapper;

    @Resource
    AmmeterUsageMapper usageMapper;

    @Resource
    LadderedElecpriceMapper ladderedElecpriceMapper;



    @Scheduled(cron = "0 0 0 * * ?")
    public void refreshMonthAmount(){
        List<Ammeter> ammeterList = ammeterMapper.findAll();
        ammeterList.parallelStream().forEach(ammeter -> process(ammeter));
    }

    @Transactional
    public void process(Ammeter ammeter){
        BigDecimal dayUsage = ammeter.getReadValue().subtract(ammeter.getPreReadValue());
        dayUsage = dayUsage.compareTo(BigDecimal.ZERO) == -1 ? BigDecimal.ZERO : dayUsage;
        ammeter.setPreReadTime(ammeter.getReadTime());
        ammeter.setPreReadValue(ammeter.getReadValue());
        ammeter.setState(0);

        AmmeterUsage ammeterUsage = new AmmeterUsage();
        ammeterUsage.setAmmeterNo(ammeter.getAmmeterNo());
        ammeterUsage.setReadDate(TimeUtils.getYesterday());
        ammeterUsage.setReadTs(TimeUtils.getUnixTimestamp(ammeterUsage.getReadDate()));
        ammeterUsage.setReadValue(ammeter.getReadValue());

        int uid = ammeter.getuId();
        AmmeterCost ammeterCost = new AmmeterCost();
        ammeterCost.setAmmeterNo(ammeter.getAmmeterNo());
        ammeterCost.setuId(uid);
        ammeterCost.setCostDate(TimeUtils.getYesterday());
        ammeterCost.setCostTime(System.currentTimeMillis()/1000);
        ammeterCost.setEnprNo(ammeter.getEnprNo());

        if(ammeter.getIsUpdate() == 1){
            ammeter.setMonthAmount(ammeter.getMonthAmount().add(dayUsage));
            ammeterUsage.setAmmeterUsage(dayUsage);
            ammeterCost.setAmmeterUsage(dayUsage);
            BigDecimal cost = BigDecimal.ZERO;
            String priceType = ammeter.getPriceType();
            if(priceType.equals("NORMAL")) { //普通计费
                int voltageType = ammeter.getVoltageType();
                LadderedElecprice singlePrice = ladderedElecpriceMapper.getSingleLadder(ammeter.getEnprNo(), voltageType, 1);
                cost = getNormalCost(singlePrice, dayUsage);
                ammeterCost.setCostMoney(cost);
            } else if(priceType.equals("LADDER")) { //阶梯计费
                int voltageType = ammeter.getVoltageType();
                List<LadderedElecprice> levels = ladderedElecpriceMapper.getPriceList(ammeter.getEnprNo(), voltageType);
                cost = getLadderCost(levels, dayUsage, ammeter.getMonthAmount());
                ammeterCost.setCostMoney(cost);
            } else if(priceType.equals("LEVEL")) { //分时段计费
                BigDecimal sharpDailyUsage = ammeter.getSharpReadValue().subtract(ammeter.getPreSharpReadValue());
                BigDecimal peekDailyUsage = ammeter.getPeekReadValue().subtract(ammeter.getPrePeekReadValue());
                BigDecimal flatDailyUsage = ammeter.getFlatReadValue().subtract(ammeter.getPreFlatReadValue());
                BigDecimal lowDailyUsage = ammeter.getLowReadValue().subtract(ammeter.getPreLowReadValue());
                ammeter.setPreSharpReadValue(ammeter.getSharpReadValue());
                ammeter.setPreSharpReadTime(ammeter.getSharpReadTime());
                ammeter.setPrePeekReadValue(ammeter.getPeekReadValue());
                ammeter.setPrePeekReadTime(ammeter.getPeekReadTime());
                ammeter.setPreFlatReadValue(ammeter.getFlatReadValue());
                ammeter.setPreFlatReadTime(ammeter.getFlatReadTime());
                ammeter.setPreLowReadValue(ammeter.getLowReadValue());
                ammeter.setPreLowReadTime(ammeter.getLowReadTime());
                ammeterUsage.setSharpUsage(sharpDailyUsage);
                ammeterUsage.setPeekUsage(peekDailyUsage);
                ammeterUsage.setFlatUsage(flatDailyUsage);
                ammeterUsage.setLowUsage(lowDailyUsage);
                List<AmmeterRate> rates = rateMapper.getRates(ammeter.getEnprNo());
                BigDecimal sharpPrice = BigDecimal.ZERO;
                BigDecimal peekPrice = BigDecimal.ZERO;
                BigDecimal flatPrice = BigDecimal.ZERO;
                BigDecimal lowPrice = BigDecimal.ZERO;
                for(AmmeterRate rate : rates) {
                    if(rate.getRateType() == 0) sharpPrice = rate.getRatePrice();
                    if(rate.getRateType() == 1) peekPrice = rate.getRatePrice();
                    if(rate.getRateType() == 2) flatPrice = rate.getRatePrice();
                    if(rate.getRateType() == 3) lowPrice = rate.getRatePrice();
                }
                BigDecimal sharpCost = sharpDailyUsage.multiply(sharpPrice);
                BigDecimal peekCost = peekDailyUsage.multiply(peekPrice);
                BigDecimal flatCost = flatDailyUsage.multiply(flatPrice);
                BigDecimal lowCost = lowDailyUsage.multiply(lowPrice);
                cost = sharpCost.add(peekCost).add(flatCost).add(lowCost);
                ammeterCost.setCostMoney(cost);
                ammeterCost.setSharpUsage(sharpDailyUsage);
                ammeterCost.setPeekUsage(peekDailyUsage);
                ammeterCost.setFlatUsage(flatDailyUsage);
                ammeterCost.setLowUsage(lowDailyUsage);
                ammeterCost.setSharpCost(sharpCost);
                ammeterCost.setPeekCost(peekCost);
                ammeterCost.setFlatCost(flatCost);
                ammeterCost.setLowCost(lowCost);
            } else {
                ammeterCost.setCostMoney(BigDecimal.ZERO);
            }

            User user = userMapper.findByUid(uid);
            user.setAccountBalance(user.getAccountBalance().subtract(cost));
            userMapper.saveUser(user);
        } else {
            if((System.currentTimeMillis()/1000 - ammeter.getReadTime())/24*3600 >= 2) {
                ammeter.setState(1);
            }
            ammeterUsage.setAmmeterUsage(BigDecimal.ZERO);
            ammeterCost.setCostMoney(BigDecimal.ZERO);
            ammeterCost.setAmmeterUsage(BigDecimal.ZERO);
        }
        ammeterMapper.uptAmmeterValue(ammeter);
        usageMapper.save(ammeterUsage);
        costMapper.save(ammeterCost);
    }

    private BigDecimal getNormalCost(LadderedElecprice ladder, BigDecimal dayUsage) {
        return dayUsage.multiply(ladder.getPrice());
    }

    private BigDecimal getLadderCost(List<LadderedElecprice> list, BigDecimal dayUsage, BigDecimal monthAmount){
        int levelCnt = list.size();
        BigDecimal[] prices = new BigDecimal[levelCnt];   //阶梯电费单价
        BigDecimal[] ladders = new BigDecimal[levelCnt];  //阶梯电费起始kwh
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
        if(curLevel == 0) {
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
