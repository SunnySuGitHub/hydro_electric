package com.hust.hydroelectric_backend.Dao;

import com.hust.hydroelectric_backend.Entity.Ammeters.AmmeterCost;
import com.hust.hydroelectric_backend.Entity.VO.MeterDailyCost;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/23 23:01
 */
@Repository
public interface AmmeterCostMapper {
    List<MeterDailyCost> getAmmeterTotalDailyCost(@Param("meterNo") String meterNo, @Param("enprNo") String enprNo);
    List<MeterDailyCost> getAmmeterSharpDailyCost(@Param("meterNo") String meterNo, @Param("enprNo") String enprNo);
    List<MeterDailyCost> getAmmeterPeekDailyCost(@Param("meterNo") String meterNo, @Param("enprNo") String enprNo);
    List<MeterDailyCost> getAmmeterFlatDailyCost(@Param("meterNo") String meterNo, @Param("enprNo") String enprNo);
    List<MeterDailyCost> getAmmeterLowDailyCost(@Param("meterNo") String meterNo, @Param("enprNo") String enprNo);
    int save(AmmeterCost ammeterCost);
}
