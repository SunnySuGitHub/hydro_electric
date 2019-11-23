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
    private Long costTime;
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

    public Long getCostTime() {
        return costTime;
    }

    public void setCostTime(Long costTime) {
        this.costTime = costTime;
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
