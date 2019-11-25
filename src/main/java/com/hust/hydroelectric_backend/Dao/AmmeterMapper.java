package com.hust.hydroelectric_backend.Dao;

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
    List<Ammeter> findAll();
    int uptAmmeterValue(Ammeter ammeter);
    List<Ammeter> findFailedAmmeters(@Param("cid") int cid);
    List<Ammeter> findAmmeterByUid(@Param("uid") int uid);
}
