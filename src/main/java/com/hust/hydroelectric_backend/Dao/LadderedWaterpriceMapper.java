package com.hust.hydroelectric_backend.Dao;

import com.hust.hydroelectric_backend.Entity.Watermeters.LadderedWaterprice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/26 15:47
 */
@Repository
public interface LadderedWaterpriceMapper {
    List<LadderedWaterprice> getLevels(@Param("enprNo") String enprNo, @Param("waterType") int type);
}
