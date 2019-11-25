package com.hust.hydroelectric_backend.Entity.Watermeters;

import java.math.BigDecimal;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/25 15:11
 */
public class WatermeterUsage {
    private Integer id;
    private String watermeterNo;
    private BigDecimal watermeterUsaga;
    private Long endTime;
    private BigDecimal endValue;
    private String enprNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWatermeterNo() {
        return watermeterNo;
    }

    public void setWatermeterNo(String watermeterNo) {
        this.watermeterNo = watermeterNo;
    }

    public BigDecimal getWatermeterUsaga() {
        return watermeterUsaga;
    }

    public void setWatermeterUsaga(BigDecimal watermeterUsaga) {
        this.watermeterUsaga = watermeterUsaga;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getEndValue() {
        return endValue;
    }

    public void setEndValue(BigDecimal endValue) {
        this.endValue = endValue;
    }

    public String getEnprNo() {
        return enprNo;
    }

    public void setEnprNo(String enprNo) {
        this.enprNo = enprNo;
    }
}
