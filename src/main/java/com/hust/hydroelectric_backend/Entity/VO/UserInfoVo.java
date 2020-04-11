package com.hust.hydroelectric_backend.Entity.VO;

import java.math.BigDecimal;

/**
 * Description:hydroelectric_backend
 * Created by Administrator on 2019/11/20
 */
public class UserInfoVo {
    private Integer uId;
    private String uName;
    private String uTel;
    private String address;
    private BigDecimal accountBalance;
    private Integer meterType;
    private String meterNo;
    private Long readTime;
    private BigDecimal readValue;
    private Integer state;

//    public UserInfoVo(Integer uId, String uName, String uTel, String address, BigDecimal accountBalance, Integer meterType, String meterNo, Long readTime, BigDecimal readValue, Integer state) {
//        this.uId = uId;
//        this.uName = uName;
//        this.uTel = uTel;
//        this.address = address;
//        this.accountBalance = accountBalance;
//        this.meterType = meterType;
//        this.meterNo = meterNo;
//        this.readTime = readTime;
//        this.readValue = readValue;
//        this.state = state;
//    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuTel() {
        return uTel;
    }

    public void setuTel(String uTel) {
        this.uTel = uTel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Integer getMeterType() {
        return meterType;
    }

    public void setMeterType(Integer meterType) {
        this.meterType = meterType;
    }

    public String getMeterNo() {
        return meterNo;
    }

    public void setMeterNo(String meterNo) {
        this.meterNo = meterNo;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
