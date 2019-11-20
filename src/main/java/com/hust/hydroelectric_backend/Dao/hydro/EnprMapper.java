package com.hust.hydroelectric_backend.Dao.hydro;

import com.hust.hydroelectric_backend.Entity.Enpr;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/20 14:59
 */
@Repository
public interface EnprMapper {
    int addEnpr(Enpr enpr);
    List<Enpr> findAll();
}
