package com.hust.hydroelectric_backend.Dao;

import com.github.pagehelper.Page;
import com.hust.hydroelectric_backend.Entity.Repair;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/20 10:58
 */
public interface RepairMapper {
    int save(Repair repair);
    Page<Repair> list(@Param("enprNo") String enprNo, @Param("state") int state);
    int uptRepair(Repair repair);
}
