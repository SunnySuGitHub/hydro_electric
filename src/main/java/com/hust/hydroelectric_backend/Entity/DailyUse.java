package com.hust.hydroelectric_backend.Entity;

import java.math.BigDecimal;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/20 15:48
 */
public class DailyUse {
    private Integer id;
    private String meterNo;
    private BigDecimal dailyAmount;
    private Integer meterType;//0代表水表 1代表电表
    private Long dateline;
    private Integer cId;
    private String enprNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMeterNo() {
        return meterNo;
    }

    public void setMeterNo(String meterNo) {
        this.meterNo = meterNo;
    }

    public BigDecimal getDailyAmount() {
        return dailyAmount;
    }

    public void setDailyAmount(BigDecimal dailyAmount) {
        this.dailyAmount = dailyAmount;
    }

    public Integer getMeterType() {
        return meterType;
    }

    public void setMeterType(Integer meterType) {
        this.meterType = meterType;
    }

    public Long getDateline() {
        return dateline;
    }

    public void setDateline(Long dateline) {
        this.dateline = dateline;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getEnprNo() {
        return enprNo;
    }

    public void setEnprNo(String enprNo) {
        this.enprNo = enprNo;
    }
}
