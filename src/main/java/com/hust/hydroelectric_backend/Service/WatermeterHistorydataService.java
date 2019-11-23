package com.hust.hydroelectric_backend.Service;

import com.hust.hydroelectric_backend.Dao.hydro.WatermeterHistoryMapper;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/23 17:08
 */
@Service
public class WatermeterHistorydataService {

    @Resource
    WatermeterHistoryMapper watermeterHistoryMapper;


    public ResultData getWatermeterHistorydata(String meterNo, String enprNo, long startLine, long endLine){
        if(StringUtils.isNotEmpty(meterNo) && StringUtils.isNotEmpty(enprNo)) {
            return Result.success(watermeterHistoryMapper.getWatermeterHistoryByMeterNo(meterNo, enprNo, startLine, endLine));
        } else {
            return Result.error(HttpStatus.BAD_REQUEST, "参数错误");
        }
    }

    public BigDecimal getLatestRecordByMeterNo(String meterNo, String enprNo){
        return watermeterHistoryMapper.getLatestRecordByMeterNo(meterNo, enprNo);
    }
}
