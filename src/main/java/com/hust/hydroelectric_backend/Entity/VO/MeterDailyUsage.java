package com.hust.hydroelectric_backend.Entity.VO;


import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/19 20:10
 */
public class MeterDailyUsage {
    private String dateline;
    private BigDecimal dailyUse;
    private Integer meterType;

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    public BigDecimal getDailyUse() {
        return dailyUse;
    }

    public void setDailyUse(BigDecimal dailyUse) {
        this.dailyUse = dailyUse;
    }

    public Integer getMeterType() {
        return meterType;
    }

    public void setMeterType(Integer meterType) {
        this.meterType = meterType;
    }
}
