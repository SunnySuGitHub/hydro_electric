package com.hust.hydroelectric_backend.Dao.hydro;

import com.hust.hydroelectric_backend.Entity.RunningDevice;
import com.hust.hydroelectric_backend.Entity.Watermeter;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/19 9:37
 */
@Repository
public interface DeviceMapper {
    RunningDevice getRunningCnt(int cId);
    Watermeter findByMeternoAndEnprNo(@Param("meterNo") String meterNo, @Param("enprNo") String enprNo);
    int saveMeter(Watermeter watermeter);
    Set<String> findAllMeterNoByEnprNo(@Param("enprNo") String enprNo);
}
