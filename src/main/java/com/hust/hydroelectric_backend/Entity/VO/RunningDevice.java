package com.hust.hydroelectric_backend.Entity.VO;


/**
 * @author: suxinyu
 * @DateTme: 2019/11/19 9:34
 */
public class RunningDevice {
    private Integer cId;
    private String cName;
    private Integer waterMeterCnt;
    private Integer waterMeterSucCnt;
    private Integer ammeterCnt;
    private Integer ammeterSucCnt;

    public RunningDevice(Integer cId, String cName, Integer waterMeterCnt, Integer waterMeterSucCnt, Integer ammeterCnt, Integer ammeterSucCnt) {
        this.cId = cId;
        this.cName = cName;
        this.waterMeterCnt = waterMeterCnt;
        this.waterMeterSucCnt = waterMeterSucCnt;
        this.ammeterCnt = ammeterCnt;
        this.ammeterSucCnt = ammeterSucCnt;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public Integer getWaterMeterCnt() {
        return waterMeterCnt;
    }

    public void setWaterMeterCnt(Integer waterMeterCnt) {
        this.waterMeterCnt = waterMeterCnt;
    }

    public Integer getWaterMeterSucCnt() {
        return waterMeterSucCnt;
    }

    public void setWaterMeterSucCnt(Integer waterMeterSucCnt) {
        this.waterMeterSucCnt = waterMeterSucCnt;
    }

    public Integer getAmmeterCnt() {
        return ammeterCnt;
    }

    public void setAmmeterCnt(Integer ammeterCnt) {
        this.ammeterCnt = ammeterCnt;
    }

    public Integer getAmmeterSucCnt() {
        return ammeterSucCnt;
    }

    public void setAmmeterSucCnt(Integer ammeterSucCnt) {
        this.ammeterSucCnt = ammeterSucCnt;
    }
}
