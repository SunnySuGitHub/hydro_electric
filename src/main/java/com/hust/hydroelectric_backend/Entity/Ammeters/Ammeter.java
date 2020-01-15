package com.hust.hydroelectric_backend.Entity.Ammeters;

import java.math.BigDecimal;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/19 11:54
 * 电表
 */
public class Ammeter {
    private Integer ammeterId;
    private String ammeterNo;
    private Integer uId;
    private Integer cId;
    private Long installTime;
    private BigDecimal readValue;
    private Long readTime;
    private BigDecimal preReadValue;
    private Long preReadTime;
    private BigDecimal monthAmount;
    private String priceType;

    private BigDecimal sharpReadValue;
    private Long sharpReadTime;
    private BigDecimal preSharpReadValue;
    private Long preSharpReadTime;

    private BigDecimal peekReadValue;
    private Long peekReadTime;
    private BigDecimal prePeekReadValue;
    private Long prePeekReadTime;

    private BigDecimal flatReadValue;
    private Long flatReadTime;
    private BigDecimal preFlatReadValue;
    private Long preFlatReadTime;

    private BigDecimal lowReadValue;
    private Long lowReadTime;
    private BigDecimal preLowReadValue;
    private Long preLowReadTime;

    private Integer state;
    private Integer voltageType;
    private String enprNo;
    private Integer centerId;
    private Integer isUpdate;
    private Integer isDelete;

    public Integer getAmmeterId() {
        return ammeterId;
    }

    public void setAmmeterId(Integer ammeterId) {
        this.ammeterId = ammeterId;
    }

    public String getAmmeterNo() {
        return ammeterNo;
    }

    public void setAmmeterNo(String ammeterNo) {
        this.ammeterNo = ammeterNo;
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

    public Long getInstallTime() {
        return installTime;
    }

    public void setInstallTime(Long installTime) {
        this.installTime = installTime;
    }

    public BigDecimal getReadValue() {
        return readValue;
    }

    public void setReadValue(BigDecimal readValue) {
        this.readValue = readValue;
    }

    public Long getReadTime() {
        return readTime;
    }

    public void setReadTime(Long readTime) {
        this.readTime = readTime;
    }

    public BigDecimal getPreReadValue() {
        return preReadValue;
    }

    public void setPreReadValue(BigDecimal preReadValue) {
        this.preReadValue = preReadValue;
    }

    public Long getPreReadTime() {
        return preReadTime;
    }

    public void setPreReadTime(Long preReadTime) {
        this.preReadTime = preReadTime;
    }

    public BigDecimal getMonthAmount() {
        return monthAmount;
    }

    public void setMonthAmount(BigDecimal monthAmount) {
        this.monthAmount = monthAmount;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public BigDecimal getSharpReadValue() {
        return sharpReadValue;
    }

    public void setSharpReadValue(BigDecimal sharpReadValue) {
        this.sharpReadValue = sharpReadValue;
    }

    public Long getSharpReadTime() {
        return sharpReadTime;
    }

    public void setSharpReadTime(Long sharpReadTime) {
        this.sharpReadTime = sharpReadTime;
    }

    public BigDecimal getPreSharpReadValue() {
        return preSharpReadValue;
    }

    public void setPreSharpReadValue(BigDecimal preSharpReadValue) {
        this.preSharpReadValue = preSharpReadValue;
    }

    public Long getPreSharpReadTime() {
        return preSharpReadTime;
    }

    public void setPreSharpReadTime(Long preSharpReadTime) {
        this.preSharpReadTime = preSharpReadTime;
    }

    public BigDecimal getPeekReadValue() {
        return peekReadValue;
    }

    public void setPeekReadValue(BigDecimal peekReadValue) {
        this.peekReadValue = peekReadValue;
    }

    public Long getPeekReadTime() {
        return peekReadTime;
    }

    public void setPeekReadTime(Long peekReadTime) {
        this.peekReadTime = peekReadTime;
    }

    public BigDecimal getPrePeekReadValue() {
        return prePeekReadValue;
    }

    public void setPrePeekReadValue(BigDecimal prePeekReadValue) {
        this.prePeekReadValue = prePeekReadValue;
    }

    public Long getPrePeekReadTime() {
        return prePeekReadTime;
    }

    public void setPrePeekReadTime(Long prePeekReadTime) {
        this.prePeekReadTime = prePeekReadTime;
    }

    public BigDecimal getFlatReadValue() {
        return flatReadValue;
    }

    public void setFlatReadValue(BigDecimal flatReadValue) {
        this.flatReadValue = flatReadValue;
    }

    public Long getFlatReadTime() {
        return flatReadTime;
    }

    public void setFlatReadTime(Long flatReadTime) {
        this.flatReadTime = flatReadTime;
    }

    public BigDecimal getPreFlatReadValue() {
        return preFlatReadValue;
    }

    public void setPreFlatReadValue(BigDecimal preFlatReadValue) {
        this.preFlatReadValue = preFlatReadValue;
    }

    public Long getPreFlatReadTime() {
        return preFlatReadTime;
    }

    public void setPreFlatReadTime(Long preFlatReadTime) {
        this.preFlatReadTime = preFlatReadTime;
    }

    public BigDecimal getLowReadValue() {
        return lowReadValue;
    }

    public void setLowReadValue(BigDecimal lowReadValue) {
        this.lowReadValue = lowReadValue;
    }

    public Long getLowReadTime() {
        return lowReadTime;
    }

    public void setLowReadTime(Long lowReadTime) {
        this.lowReadTime = lowReadTime;
    }

    public BigDecimal getPreLowReadValue() {
        return preLowReadValue;
    }

    public void setPreLowReadValue(BigDecimal preLowReadValue) {
        this.preLowReadValue = preLowReadValue;
    }

    public Long getPreLowReadTime() {
        return preLowReadTime;
    }

    public void setPreLowReadTime(Long preLowReadTime) {
        this.preLowReadTime = preLowReadTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getVoltageType() {
        return voltageType;
    }

    public void setVoltageType(Integer voltageType) {
        this.voltageType = voltageType;
    }

    public String getEnprNo() {
        return enprNo;
    }

    public void setEnprNo(String enprNo) {
        this.enprNo = enprNo;
    }

    public Integer getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Integer isUpdate) {
        this.isUpdate = isUpdate;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getCenterId() {
        return centerId;
    }

    public void setCenterId(Integer centerId) {
        this.centerId = centerId;
    }
}
