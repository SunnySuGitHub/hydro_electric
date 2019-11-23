package com.hust.hydroelectric_backend.Dao.hydro;

import com.hust.hydroelectric_backend.Entity.AmmeterHistorydata;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/23 19:49
 */
@Repository
public interface AmmeterHistoryMapper {
    List<AmmeterHistorydata> getAmmeterHistorydata(@Param("meterNo") String meterNo, @Param("enprNo") String enprNo,
                                                   @Param("startLine") long startLine, @Param("endLine") long endLine);
}
