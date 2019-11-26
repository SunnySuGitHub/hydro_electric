package com.hust.hydroelectric_backend.Entity.Watermeters;

import java.math.BigDecimal;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/26 15:21
 */
public class WatermeterCost {
    private Integer id;
    private Integer uId;
    private BigDecimal watermeterUsage;
    private BigDecimal costMoney;
    private Long costTime;
    private String watermeterNo;
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

    public BigDecimal getWatermeterUsage() {
        return watermeterUsage;
    }

    public void setWatermeterUsage(BigDecimal watermeterUsage) {
        this.watermeterUsage = watermeterUsage;
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

    public String getWatermeterNo() {
        return watermeterNo;
    }

    public void setWatermeterNo(String watermeterNo) {
        this.watermeterNo = watermeterNo;
    }

    public String getEnprNo() {
        return enprNo;
    }

    public void setEnprNo(String enprNo) {
        this.enprNo = enprNo;
    }
}
