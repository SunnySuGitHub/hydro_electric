package com.hust.hydroelectric_backend.Dao;

import com.github.pagehelper.Page;
import com.hust.hydroelectric_backend.Entity.Payhistory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/27 16:10
 */
@Repository
public interface PayhistoryMapper {
    int save(Payhistory payhistory);
    int update(Payhistory payhistory);
    Payhistory findById(@Param("id") int id);
    Page<Payhistory> getPayHistory(@Param("enprNo") String enprNo, @Param("startLine") long startLine, @Param("endLine") long endLine);
    Page<Payhistory> getOperatorPayHistory(@Param("operatorId") int operatorId, @Param("startLine") long startLine, @Param("endLine") long endLine);
    Page<Payhistory> getUserPayHistory(@Param("uId") int uId, @Param("startLine") long startLine, @Param("endLine") long endLine);
}
