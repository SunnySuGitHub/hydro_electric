package com.hust.hydroelectric_backend.Dao.hydro;

import com.hust.hydroelectric_backend.Entity.DailyUse;
import com.hust.hydroelectric_backend.Entity.MeterUseDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/20 15:53l
 */
@Repository
public interface DailyUseMapper {
    int saveDailyUse(DailyUse dailyUse);
    List<MeterUseDetail> getDailyUseDetail(@Param("cid")int cid, @Param("startLine")long startLine, @Param("endLine")long endLine, @Param("meterType") int type);
}
