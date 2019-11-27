package com.hust.hydroelectric_backend.Entity.VO;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/27 15:07
 */
public class UserDailyCostVO {
    private BigDecimal dailyCost;
    private Date dateline;

    public BigDecimal getDailyCost() {
        return dailyCost;
    }

    public void setDailyCost(BigDecimal dailyCost) {
        this.dailyCost = dailyCost;
    }

    public Date getDateline() {
        return dateline;
    }

    public void setDateline(Date dateline) {
        this.dateline = dateline;
    }
}
