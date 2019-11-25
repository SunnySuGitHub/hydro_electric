package com.hust.hydroelectric_backend.Dao;

import com.hust.hydroelectric_backend.Entity.VO.MeterDailyUsage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/25 15:19
 */
@Repository
public interface WatermeterUsageMapper {
    List<MeterDailyUsage> getWatemeterDailyUsage(@Param("meterNo") String meterNo, @Param("enprNo") String enprNo);
    BigDecimal getCommunityUsage(@Param("cid") int cid, @Param("startLine") long startLine, @Param("endLine") long endLine, @Param("enprNo") String enprNo);
}
