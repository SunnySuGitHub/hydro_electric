package com.hust.hydroelectric_backend.Entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/19 20:10
 */
public class WaterMeterUseDetail {
    private Date dateline;
    private BigDecimal dailyUse;

    public Date getDateline() {
        return dateline;
    }

    public void setDateline(Date dateline) {
        this.dateline = dateline;
    }

    public BigDecimal getDailyUse() {
        return dailyUse;
    }

    public void setDailyUse(BigDecimal dailyUse) {
        this.dailyUse = dailyUse;
    }
}
