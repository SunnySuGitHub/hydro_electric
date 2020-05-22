package com.hust.hydroelectric_backend.Dao;

import com.github.pagehelper.Page;
import com.hust.hydroelectric_backend.Entity.VO.MeterDailyCost;
import com.hust.hydroelectric_backend.Entity.Watermeters.WatermeterCost;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/25 15:45
 */
@Repository
public interface WatermeterCostMapper {
    Page<MeterDailyCost> getWatermeterDailyCost(@Param("meterNo") String meterNo, @Param("enprNo") String enprNo);
    int save(WatermeterCost watermeterCost);
}
