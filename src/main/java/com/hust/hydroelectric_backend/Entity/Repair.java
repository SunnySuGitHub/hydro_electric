package com.hust.hydroelectric_backend.Entity;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/20 10:54
 */
public class Repair {
    private Integer repairId;
    private Integer uId;
    private String uNo;
    private String uTel;
    private String uName;
    private String uAddr;
    private String repairmanName;
    private String repairmanTel;
    private Long submitTime;
    private Integer isFeedback;
    private String problem;
    private Integer state;
    private Integer cId;

    public Integer getRepairId() {
        return repairId;
    }

    public void setRepairId(Integer repairId) {
        this.repairId = repairId;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getuNo() {
        return uNo;
    }

    public void setuNo(String uNo) {
        this.uNo = uNo;
    }

    public String getuTel() {
        return uTel;
    }

    public void setuTel(String uTel) {
        this.uTel = uTel;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuAddr() {
        return uAddr;
    }

    public void setuAddr(String uAddr) {
        this.uAddr = uAddr;
    }

    public String getRepairmanName() {
        return repairmanName;
    }

    public void setRepairmanName(String repairmanName) {
        this.repairmanName = repairmanName;
    }

    public String getRepairmanTel() {
        return repairmanTel;
    }

    public void setRepairmanTel(String repairmanTel) {
        this.repairmanTel = repairmanTel;
    }

    public Long getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Long submitTime) {
        this.submitTime = submitTime;
    }

    public Integer getIsFeedback() {
        return isFeedback;
    }

    public void setIsFeedback(Integer isFeedback) {
        this.isFeedback = isFeedback;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }
}
