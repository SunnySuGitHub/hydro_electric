package com.hust.hydroelectric_backend.Entity.Ammeters;

import java.math.BigDecimal;

/**
 * @author: suxinyu
 * @DateTme: 2019/12/10 19:46
 * 电表费率表
 */
public class AmmeterRate {
    private Integer id;
    private Integer rateType;
    private String readTime;
    private BigDecimal ratePrice;
    private String enprNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRateType() {
        return rateType;
    }

    public void setRateType(Integer rateType) {
        this.rateType = rateType;
    }

    public String getReadTime() {
        return readTime;
    }

    public void setReadTime(String readTime) {
        this.readTime = readTime;
    }

    public BigDecimal getRatePrice() {
        return ratePrice;
    }

    public void setRatePrice(BigDecimal ratePrice) {
        this.ratePrice = ratePrice;
    }

    public String getEnprNo() {
        return enprNo;
    }

    public void setEnprNo(String enprNo) {
        this.enprNo = enprNo;
    }
}
