package com.hust.hydroelectric_backend.Dao;

import com.hust.hydroelectric_backend.Entity.Ammeters.LadderedElecprice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/26 15:47
 */
@Repository
public interface LadderedElecpriceMapper {
    LadderedElecprice getSingleLadder(@Param("enprNo") String enprNo, @Param("voltageType") int voltageType, @Param("costType") int costType);
    List<LadderedElecprice> getPriceList(@Param("enprNo") String enprNo, @Param("voltageType") int voltageType);
}
