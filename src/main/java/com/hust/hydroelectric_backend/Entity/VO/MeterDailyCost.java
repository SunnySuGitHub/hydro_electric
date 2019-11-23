package com.hust.hydroelectric_backend.Entity.VO;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/23 23:04
 */
public class MeterDailyCost {
    private Date dateline;
    private BigDecimal dailyCost;

    public Date getDateline() {
        return dateline;
    }

    public void setDateline(Date dateline) {
        this.dateline = dateline;
    }

    public BigDecimal getDailyCost() {
        return dailyCost;
    }

    public void setDailyCost(BigDecimal dailyCost) {
        this.dailyCost = dailyCost;
    }
}
