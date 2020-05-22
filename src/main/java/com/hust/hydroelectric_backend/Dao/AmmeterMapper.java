package com.hust.hydroelectric_backend.Dao;

import com.github.pagehelper.Page;
import com.hust.hydroelectric_backend.Entity.Ammeters.Ammeter;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/19 20:06
 */
@Repository
public interface AmmeterMapper {
    Ammeter getAmmeterDetail(@Param("ammeterNo") String ammeterNo, @Param("enprNo") String enprNo);
    Page<Ammeter> getAllAmmeterDetail(@Param("enprNo") String enprNo, @Param("state") int state);
    List<Ammeter> findAll();
    int uptAmmeterValue(Ammeter ammeter);
    int delete(@Param("meterNo") String meterNo, @Param("enprNo") String enprNo);
    List<Ammeter> findFailedAmmeters(@Param("cid") int cid);
    List<Ammeter> findAmmeterByUid(@Param("uid") int uid);
    Page<Ammeter> getAmmeterByCenter(@Param("centerId") int centerId);
}
