package com.hust.hydroelectric_backend.Entity.Ammeters;

import java.math.BigDecimal;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/23 19:08
 */
public class AmmeterUsage {
    private Integer id;
    private String ammeterNo;
    private BigDecimal ammeterUsage;
    private BigDecimal sharpUsage;
    private BigDecimal peekUsage;
    private BigDecimal flatUsage;
    private BigDecimal lowUsage;
    private String readDate;
    private Long readTs;
    private BigDecimal readValue;
    private String enprNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAmmeterNo() {
        return ammeterNo;
    }

    public void setAmmeterNo(String ammeterNo) {
        this.ammeterNo = ammeterNo;
    }

    public BigDecimal getAmmeterUsage() {
        return ammeterUsage;
    }

    public void setAmmeterUsage(BigDecimal ammeterUsage) {
        this.ammeterUsage = ammeterUsage;
    }

    public BigDecimal getSharpUsage() {
        return sharpUsage;
    }

    public void setSharpUsage(BigDecimal sharpUsage) {
        this.sharpUsage = sharpUsage;
    }

    public BigDecimal getPeekUsage() {
        return peekUsage;
    }

    public void setPeekUsage(BigDecimal peekUsage) {
        this.peekUsage = peekUsage;
    }

    public BigDecimal getFlatUsage() {
        return flatUsage;
    }

    public void setFlatUsage(BigDecimal flatUsage) {
        this.flatUsage = flatUsage;
    }

    public BigDecimal getLowUsage() {
        return lowUsage;
    }

    public void setLowUsage(BigDecimal lowUsage) {
        this.lowUsage = lowUsage;
    }

    public String getReadDate() {
        return readDate;
    }

    public void setReadDate(String readDate) {
        this.readDate = readDate;
    }

    public Long getReadTs() {
        return readTs;
    }

    public void setReadTs(Long readTs) {
        this.readTs = readTs;
    }

    public BigDecimal getReadValue() {
        return readValue;
    }

    public void setReadValue(BigDecimal readValue) {
        this.readValue = readValue;
    }

    public String getEnprNo() {
        return enprNo;
    }

    public void setEnprNo(String enprNo) {
        this.enprNo = enprNo;
    }
}
