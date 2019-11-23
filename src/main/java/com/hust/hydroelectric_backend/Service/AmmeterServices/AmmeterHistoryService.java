package com.hust.hydroelectric_backend.Service.AmmeterServices;

import com.hust.hydroelectric_backend.Dao.hydro.AmmeterHistoryMapper;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/23 19:43
 */
@Service
public class AmmeterHistoryService {

    @Resource
    AmmeterHistoryMapper ammeterHistoryMapper;

    public ResultData getAmmeterHistorydata(String meterNo, String enprNo, long startLine, long endLine){
        return Result.success(ammeterHistoryMapper.getAmmeterHistorydata(meterNo, enprNo, startLine, endLine));
    }


}
