package com.hust.hydroelectric_backend.Dao;

import com.hust.hydroelectric_backend.Entity.Watermeters.WatermeterHistorydata;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/23 17:08
 */
@Repository
public interface WatermeterHistoryMapper {
    List<WatermeterHistorydata> getWatermeterHistoryByMeterNo(@Param("meterNo") String meterNo, @Param("enprNo") String enprNo,
                                                              @Param("startLine") long startLine, @Param("endLine") long endLine);
    BigDecimal getLatestRecordByMeterNo(@Param("meterNo") String meterNo, @Param("enprNo") String enprNo);
}
