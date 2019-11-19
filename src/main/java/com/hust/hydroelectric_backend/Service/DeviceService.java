package com.hust.hydroelectric_backend.Service;

import com.hust.hydroelectric_backend.Dao.hydro.DeviceMapper;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/19 9:32
 */
@Service
public class DeviceService {

    @Resource
    DeviceMapper deviceMapper;

    public ResultData getRunningCnt(int cId){
        return Result.success(deviceMapper.getRunningCnt(cId));
    }


}
