package com.hust.hydroelectric_backend.Dao;

import com.hust.hydroelectric_backend.Entity.Ammeters.AmmeterUsage;
import com.hust.hydroelectric_backend.Entity.VO.MeterDailyUsage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/23 19:06
 */
@Repository
public interface AmmeterUsageMapper {
    List<MeterDailyUsage> getAmmeterTotalDailyUsage(@Param("ammeterNo") String ammeterNo, @Param("enprNo") String enprNo);
    List<MeterDailyUsage> getAmmeterSharpDailyUsage(@Param("ammeterNo") String ammeterNo, @Param("enprNo") String enprNo);
    List<MeterDailyUsage> getAmmeterPeekDailyUsage(@Param("ammeterNo") String ammeterNo, @Param("enprNo") String enprNo);
    List<MeterDailyUsage> getAmmeterFlatDailyUsage(@Param("ammeterNo") String ammeterNo, @Param("enprNo") String enprNo);
    List<MeterDailyUsage> getAmmeterLowDailyUsage(@Param("ammeterNo") String ammeterNo, @Param("enprNo") String enprNo);
    BigDecimal getCommunityUsage(@Param("cid") int cid, @Param("startLine") long startLine, @Param("endLine") long endLine, @Param("enprNo") String enprNo);
    int save(AmmeterUsage ammeterUsage);
}
