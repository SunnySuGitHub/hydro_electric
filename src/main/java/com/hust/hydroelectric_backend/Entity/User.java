package com.hust.hydroelectric_backend.Entity;


import java.math.BigDecimal;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/18 15:36
 */
public class User {
    private Integer uId;
    private String uName;
    private String uTel;
    private Integer bId;
    private String address;
    private BigDecimal accountBalance;
    private Integer uNo;
    private String enprNo;
    private Integer isDelete;

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

    public Integer getbId() {
        return bId;
    }

    public void setbId(Integer bId) {
        this.bId = bId;
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

    public Integer getuNo() {
        return uNo;
    }

    public void setuNo(Integer uNo) {
        this.uNo = uNo;
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
