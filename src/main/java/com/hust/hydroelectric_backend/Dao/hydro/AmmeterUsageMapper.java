package com.hust.hydroelectric_backend.Dao.hydro;

import com.hust.hydroelectric_backend.Entity.Ammeters.AmmeterUsage;
import com.hust.hydroelectric_backend.Entity.VO.MeterDailyUsage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/23 19:06
 */
@Repository
public interface AmmeterUsageMapper {
    List<MeterDailyUsage> getAmmeterDailyUsage(@Param("ammeterNo") String ammeterNo, @Param("enprNo") String enprNo);
    List<AmmeterUsage> getAmmeterUsageDetail(@Param("ammeterNo") String ammeterNo, @Param("enprNo") String enprNo,
                                             @Param("startLine") long startLine, @Param("endLine") long endLine);
}
