package com.hust.hydroelectric_backend.Dao;

import com.hust.hydroelectric_backend.Entity.Operator;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/18 20:17
 */
@Repository
public interface OperatorMapper {

    Operator login(@Param("account")String account, @Param("password")String password, @Param("enprNo")String enprNo);

    int addOperator(Operator operator);

    int delOperator(@Param("operatorId") int id);

    Operator getOperator(@Param("id") int id);

    int uptOperator(Operator operator);

}
