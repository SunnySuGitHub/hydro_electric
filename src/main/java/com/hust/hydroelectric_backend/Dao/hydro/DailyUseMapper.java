package com.hust.hydroelectric_backend.Dao.hydro;

import com.hust.hydroelectric_backend.Entity.DailyUse;
import org.springframework.stereotype.Repository;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/20 15:53
 */
@Repository
public interface DailyUseMapper {
    int saveDailyUse(DailyUse dailyUse);
}
