package com.hust.hydroelectric_backend.Dao.hydro;

import com.hust.hydroelectric_backend.Entity.Watermeter;
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
    Set<String> findAllMeterNoByEnprNo(@Param("enprNo") String enprNo);
    List<Watermeter> findFailedWatermeter(int cid);
    List<Watermeter> findWatermeterByUnoAndEnprNo(@Param("uNo") String uno, @Param("enprNo") String enprNo);
}
