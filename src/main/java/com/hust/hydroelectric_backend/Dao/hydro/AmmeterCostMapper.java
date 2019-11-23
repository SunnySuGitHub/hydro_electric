package com.hust.hydroelectric_backend.Dao.hydro;

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
    List<MeterDailyCost> getAmmeterDailyCost(@Param("meterNo") String meterNo, @Param("enprNo") String enprNo);
    List<AmmeterCost> getAmmeterCostDetail(@Param("meterNo") String meterNo, @Param("enprNo") String enprNo,
                                           @Param("startLine") long startLine, @Param("endLine") long endLine);
}
