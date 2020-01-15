package com.hust.hydroelectric_backend.Entity.Ammeters;

import java.math.BigDecimal;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/26 15:46
 */
public class LadderedElecprice {
    private Integer id;
    private Integer costLevel;
    private Integer voltageType;
    private BigDecimal price;
    private BigDecimal ladder;
    private String enprNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCostLevel() {
        return costLevel;
    }

    public void setCostLevel(Integer costLevel) {
        this.costLevel = costLevel;
    }

    public Integer getVoltageType() {
        return voltageType;
    }

    public void setVoltageType(Integer voltageType) {
        this.voltageType = voltageType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getLadder() {
        return ladder;
    }

    public void setLadder(BigDecimal ladder) {
        this.ladder = ladder;
    }

    public String getEnprNo() {
        return enprNo;
    }

    public void setEnprNo(String enprNo) {
        this.enprNo = enprNo;
    }
}
