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
