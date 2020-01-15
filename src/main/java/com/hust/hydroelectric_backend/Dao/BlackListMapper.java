package com.hust.hydroelectric_backend.Dao;

import com.hust.hydroelectric_backend.Entity.BlackList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author: suxinyu
 * @DateTme: 2019/12/12 22:45
 */
@Repository
public interface BlackListMapper {
    BlackList getOperator(@Param("operatorId") int operatorId);
    int setOperatorBlackList(BlackList blackList);
}
