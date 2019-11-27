package com.hust.hydroelectric_backend.utils;

import com.github.wxpay.sdk.WXPayConfig;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/27 20:14
 * 微信支付基本配置类
 */
public class WXConfigUtil implements WXPayConfig {

    private byte[] certData;
    private String APP_ID;
    private String KEY;
    private String MCH_ID;


    public void setCertData(byte[] certData) {
        this.certData = certData;
    }


    public void setAPP_ID(String APP_ID) {
        this.APP_ID = APP_ID;
    }


    public void setKEY(String KEY) {
        this.KEY = KEY;
    }

    public void setMCH_ID(String MCH_ID) {
        this.MCH_ID = MCH_ID;
    }

    @Override
    public String getAppID() {
        return APP_ID;
    }

    @Override
    public String getMchID() {
        return MCH_ID;
    }

    @Override
    public String getKey() {
        return KEY;
    }

    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 10000;
    }
}
