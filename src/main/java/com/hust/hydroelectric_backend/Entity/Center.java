package com.hust.hydroelectric_backend.Entity;

/**
 * @author: suxinyu
 * @DateTme: 2019/12/11 15:19
 * 集中器类
 */
public class Center {
    private Integer id;
    private String centerNo;
    private String installPlace;
    private Long createTime;
    private String commProtocal;
    private Integer status;
    private Integer cId;
    private Integer isDelete;
    private String enprNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCenterNo() {
        return centerNo;
    }

    public void setCenterNo(String centerNo) {
        this.centerNo = centerNo;
    }

    public String getInstallPlace() {
        return installPlace;
    }

    public void setInstallPlace(String installPlace) {
        this.installPlace = installPlace;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getCommProtocal() {
        return commProtocal;
    }

    public void setCommProtocal(String commProtocal) {
        this.commProtocal = commProtocal;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getEnprNo() {
        return enprNo;
    }

    public void setEnprNo(String enprNo) {
        this.enprNo = enprNo;
    }
}
