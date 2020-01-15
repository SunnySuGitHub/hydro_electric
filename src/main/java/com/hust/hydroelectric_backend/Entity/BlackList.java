package com.hust.hydroelectric_backend.Entity;

/**
 * @author: suxinyu
 * @DateTme: 2019/12/12 22:41
 */
public class BlackList {
    private Integer id;
    private Integer operatorId;
    private String blackUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getBlackUrl() {
        return blackUrl;
    }

    public void setBlackUrl(String blackUrl) {
        this.blackUrl = blackUrl;
    }
}
