package com.hust.hydroelectric_backend.Entity;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/19 11:54
 * 楼栋
 */
public class Block {
    private Integer bId;
    private String bName;
    private Integer cId;
    private Integer isDelete;

    public Integer getbId() {
        return bId;
    }

    public void setbId(Integer bId) {
        this.bId = bId;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
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
}
