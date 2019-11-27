package com.hust.hydroelectric_backend.Entity.Areas;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/20 14:56
 */
public class Enpr {
    private String enprNo;
    private String enprName;
    private Long createTime;
    private String appId;
    private String wxKey;
    private String mchId;

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

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getWxKey() {
        return wxKey;
    }

    public void setWxKey(String wxKey) {
        this.wxKey = wxKey;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }
}
