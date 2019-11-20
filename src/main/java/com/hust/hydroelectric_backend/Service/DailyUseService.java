package com.hust.hydroelectric_backend.Service;

import com.hust.hydroelectric_backend.Dao.hydro.DailyUseMapper;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/20 15:52
 */
@Service
public class DailyUseService {

    @Resource
    DailyUseMapper dailyUseMapper;

    public ResultData getDailuUseDatail(int cid, long startLine, long endLine, int meterType){
        return Result.success(dailyUseMapper.getDailyUseDetail(cid, startLine, endLine, meterType));
    }
}
