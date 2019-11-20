package com.hust.hydroelectric_backend.Dao.hydro;

import com.hust.hydroelectric_backend.Entity.MeterUseDetail;
import com.hust.hydroelectric_backend.Entity.Watermeter;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/19 20:06
 */
@Repository
public interface WaterMeterMapper {
    List<MeterUseDetail> getDailyUseDetails(@Param("cId") int cId, @Param("startLine") long startLine, @Param("endLine") long endLine);
    Watermeter getWaterMeterDetail(@Param("meterNo") String meterNo, @Param("enprNo") String enprNo);
    List<Watermeter> findAll();
    int uptWatermeterValue(Watermeter watermeter);
}
