package com.hust.hydroelectric_backend.Entity.VO;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/23 23:04
 */
public class MeterDailyCost {
    private String dateline;
    private BigDecimal dailyCost;
    private Integer waterType;

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    public BigDecimal getDailyCost() {
        return dailyCost;
    }

    public void setDailyCost(BigDecimal dailyCost) {
        this.dailyCost = dailyCost;
    }

    public Integer getWaterType() {
        return waterType;
    }

    public void setWaterType(Integer waterType) {
        this.waterType = waterType;
    }
}
