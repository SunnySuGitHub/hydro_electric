package com.hust.hydroelectric_backend.Entity.Watermeters;

import java.math.BigDecimal;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/26 15:46
 */
public class LadderedWaterprice {
    private Integer id;
    private Integer costLevel;
    private Integer waterType;
    private String enprNo;
    private BigDecimal price;
    private BigDecimal ladder;

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

    public Integer getWaterType() {
        return waterType;
    }

    public void setWaterType(Integer waterType) {
        this.waterType = waterType;
    }

    public String getEnprNo() {
        return enprNo;
    }

    public void setEnprNo(String enprNo) {
        this.enprNo = enprNo;
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
}
