package com.hust.hydroelectric_backend.Dao;

import com.hust.hydroelectric_backend.Entity.Watermeters.Watermeter;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/19 20:06
 */
@Repository
public interface WaterMeterMapper {
    Watermeter getWaterMeterDetail(@Param("meterNo") String meterNo, @Param("enprNo") String enprNo);
    List<Watermeter> findAll();
    int uptWatermeterValue(Watermeter watermeter);
    int saveMeter(Watermeter watermeter);
    Set<String> findAllWatermeterNoByEnprNo(@Param("enprNo") String enprNo);
    List<Watermeter> findFailedWatermeter(int cid);
    List<Watermeter> findWatermeterByUid(@Param("uid") int uid);
}
