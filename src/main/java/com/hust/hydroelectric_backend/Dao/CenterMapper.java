package com.hust.hydroelectric_backend.Dao;

import com.hust.hydroelectric_backend.Entity.Center;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/12/11 15:22
 */
@Repository
public interface CenterMapper {
    int save(Center center);
    int delete(@Param("id") int id);
    List<Center> getCenterByEnprNo(@Param("enprNo") String enprNo);
    List<Center> getCenterByCid(@Param("cId") int cId);
}
