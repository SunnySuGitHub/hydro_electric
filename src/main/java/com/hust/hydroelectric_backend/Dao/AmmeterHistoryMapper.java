package com.hust.hydroelectric_backend.Dao;

import com.github.pagehelper.Page;
import com.hust.hydroelectric_backend.Entity.Ammeters.AmmeterHistorydata;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/23 19:49
 */
@Repository
public interface AmmeterHistoryMapper {
    Page<AmmeterHistorydata> getAmmeterHistorydata(@Param("meterNo") String meterNo, @Param("enprNo") String enprNo, @Param("readType") int readType,
                                                   @Param("startLine") long startLine, @Param("endLine") long endLine);
}
