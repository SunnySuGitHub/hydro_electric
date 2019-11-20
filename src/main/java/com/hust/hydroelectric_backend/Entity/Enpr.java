package com.hust.hydroelectric_backend.Entity;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/20 14:56
 */
public class Enpr {
    private String enprNo;
    private String enprName;
    private Long createTime;

    public String getEnprNo() {
        return enprNo;
    }

    public void setEnprNo(String enprNo) {
        this.enprNo = enprNo;
    }

    public String getEnprName() {
        return enprName;
    }

    public void setEnprName(String enprName) {
        this.enprName = enprName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
