package com.hust.hydroelectric_backend.Entity;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/19 11:54
 * 小区
 */
public class Community {
    private Integer cId;
    private String cName;
    private String enprNo;
    private Integer isDelete;

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
