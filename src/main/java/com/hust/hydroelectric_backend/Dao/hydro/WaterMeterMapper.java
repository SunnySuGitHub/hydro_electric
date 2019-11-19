package com.hust.hydroelectric_backend.Dao.hydro;

import com.hust.hydroelectric_backend.Entity.WaterMeterUseDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/19 20:06
 */
@Repository
public interface WaterMeterMapper {
    List<WaterMeterUseDetail> getDetails(@Param("cId") int cId, @Param("startLine") long startLine, @Param("endLine") long endLine);
}
