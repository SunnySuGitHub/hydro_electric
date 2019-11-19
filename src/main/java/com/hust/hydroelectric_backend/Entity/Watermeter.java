package com.hust.hydroelectric_backend.Entity;

import java.math.BigDecimal;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/19 11:54
 * 水表
 */
public class Watermeter {
    private Integer watermeterId;
    private String meterNo;
    private Integer uId;
    private Integer cId;
    private Integer caliber;
    private Long installTime;
    private Long readTime;
    private BigDecimal readValue;
    private Long preReadTime;
    private BigDecimal preReadValue;
    private BigDecimal dayAmount;
    private BigDecimal monthAmount;
    private Integer state;
    private String enprNo;
    private Integer isDelete;

    public Integer getWatermeterId() {
        return watermeterId;
    }

    public void setWatermeterId(Integer watermeterId) {
        this.watermeterId = watermeterId;
    }

    public String getMeterNo() {
        return meterNo;
    }

    public void setMeterNo(String meterNo) {
        this.meterNo = meterNo;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Integer getCaliber() {
        return caliber;
    }

    public void setCaliber(Integer caliber) {
        this.caliber = caliber;
    }

    public Long getInstallTime() {
        return installTime;
    }

    public void setInstallTime(Long installTime) {
        this.installTime = installTime;
    }

    public Long getReadTime() {
        return readTime;
    }

    public void setReadTime(Long readTime) {
        this.readTime = readTime;
    }

    public BigDecimal getReadValue() {
        return readValue;
    }

    public void setReadValue(BigDecimal readValue) {
        this.readValue = readValue;
    }

    public Long getPreReadTime() {
        return preReadTime;
    }

    public void setPreReadTime(Long preReadTime) {
        this.preReadTime = preReadTime;
    }

    public BigDecimal getPreReadValue() {
        return preReadValue;
    }

    public void setPreReadValue(BigDecimal preReadValue) {
        this.preReadValue = preReadValue;
    }

    public BigDecimal getDayAmount() {
        return dayAmount;
    }

    public void setDayAmount(BigDecimal dayAmount) {
        this.dayAmount = dayAmount;
    }

    public BigDecimal getMonthAmount() {
        return monthAmount;
    }

    public void setMonthAmount(BigDecimal monthAmount) {
        this.monthAmount = monthAmount;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getEnprNo() {
        return enprNo;
    }

    public void setEnprNo(String enprNo) {
        this.enprNo = enprNo;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
