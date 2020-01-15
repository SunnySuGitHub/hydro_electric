package com.hust.hydroelectric_backend.Entity.Ammeters;

import java.math.BigDecimal;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/23 23:11
 */
public class AmmeterCost {
    private Integer id;
    private Integer uId;
    private BigDecimal ammeterUsage;
    private BigDecimal costMoney;

    private BigDecimal sharpUsage;
    private BigDecimal sharpCost;

    private BigDecimal peekUsage;
    private BigDecimal peekCost;

    private BigDecimal flatUsage;
    private BigDecimal flatCost;

    private BigDecimal lowUsage;
    private BigDecimal lowCost;

    private Long costTime;
    private String costDate;
    private String ammeterNo;
    private String enprNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public BigDecimal getAmmeterUsage() {
        return ammeterUsage;
    }

    public void setAmmeterUsage(BigDecimal ammeterUsage) {
        this.ammeterUsage = ammeterUsage;
    }

    public BigDecimal getCostMoney() {
        return costMoney;
    }

    public void setCostMoney(BigDecimal costMoney) {
        this.costMoney = costMoney;
    }

    public BigDecimal getSharpUsage() {
        return sharpUsage;
    }

    public void setSharpUsage(BigDecimal sharpUsage) {
        this.sharpUsage = sharpUsage;
    }

    public BigDecimal getSharpCost() {
        return sharpCost;
    }

    public void setSharpCost(BigDecimal sharpCost) {
        this.sharpCost = sharpCost;
    }

    public BigDecimal getPeekUsage() {
        return peekUsage;
    }

    public void setPeekUsage(BigDecimal peekUsage) {
        this.peekUsage = peekUsage;
    }

    public BigDecimal getPeekCost() {
        return peekCost;
    }

    public void setPeekCost(BigDecimal peekCost) {
        this.peekCost = peekCost;
    }

    public BigDecimal getFlatUsage() {
        return flatUsage;
    }

    public void setFlatUsage(BigDecimal flatUsage) {
        this.flatUsage = flatUsage;
    }

    public BigDecimal getFlatCost() {
        return flatCost;
    }

    public void setFlatCost(BigDecimal flatCost) {
        this.flatCost = flatCost;
    }

    public BigDecimal getLowUsage() {
        return lowUsage;
    }

    public void setLowUsage(BigDecimal lowUsage) {
        this.lowUsage = lowUsage;
    }

    public BigDecimal getLowCost() {
        return lowCost;
    }

    public void setLowCost(BigDecimal lowCost) {
        this.lowCost = lowCost;
    }

    public Long getCostTime() {
        return costTime;
    }

    public void setCostTime(Long costTime) {
        this.costTime = costTime;
    }

    public String getCostDate() {
        return costDate;
    }

    public void setCostDate(String costDate) {
        this.costDate = costDate;
    }

    public String getAmmeterNo() {
        return ammeterNo;
    }

    public void setAmmeterNo(String ammeterNo) {
        this.ammeterNo = ammeterNo;
    }

    public String getEnprNo() {
        return enprNo;
    }

    public void setEnprNo(String enprNo) {
        this.enprNo = enprNo;
    }
}
