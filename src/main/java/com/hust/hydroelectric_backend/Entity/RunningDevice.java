package com.hust.hydroelectric_backend.Entity;

import lombok.Data;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/19 9:34
 */
@Data
public class RunningDevice {
    private Integer waterMeterCnt;
    private Integer WaterMeterSucCnt;
    private Integer ammeterCnt;
    private Integer ammeterSucCnt;
}
