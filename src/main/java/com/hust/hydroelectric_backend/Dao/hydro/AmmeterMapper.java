package com.hust.hydroelectric_backend.Dao.hydro;

import com.hust.hydroelectric_backend.Entity.Ammeter;
import com.hust.hydroelectric_backend.Entity.MeterUseDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/19 20:06
 */
@Repository
public interface AmmeterMapper {
    List<MeterUseDetail> getDailyUseDetails(@Param("cId") int cId, @Param("startLine") long startLine, @Param("endLine") long endLine);
    Ammeter getAmmeterDetail(@Param("ammeterNo") String ammeterNo, @Param("enprNo") String enprNo);
    List<Ammeter> findAll();
    int uptAmmeterValue(Ammeter ammeter);
}
