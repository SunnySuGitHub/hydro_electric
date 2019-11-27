package com.hust.hydroelectric_backend.Dao;

import com.hust.hydroelectric_backend.Entity.Payhistory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/27 16:10
 */
@Repository
public interface PayhistoryMapper {
    int save(Payhistory payhistory);
    int update(Payhistory payhistory);
    Payhistory findById(@Param("id") int id);
}
