package com.hust.hydroelectric_backend.Service.WatermeterServices;

import com.hust.hydroelectric_backend.Dao.WatermeterHistoryMapper;
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


    public ResultData getWatermeterHistorydata(String meterNo, String enprNo, long startLine, long endLine) {
        return Result.success(watermeterHistoryMapper.getWatermeterHistoryByMeterNo(meterNo, enprNo, startLine, endLine));
    }

    public BigDecimal getLatestRecordByMeterNo(String meterNo, String enprNo) {
        return watermeterHistoryMapper.getLatestRecordByMeterNo(meterNo, enprNo);
    }
}
