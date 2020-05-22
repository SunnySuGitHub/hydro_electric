package com.hust.hydroelectric_backend.Dao;

import com.hust.hydroelectric_backend.Entity.ScheduleJob;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:hydroelectric_backend
 * Created by Administrator on 2020/5/22
 */
public interface ScheduleMapper {
    List<ScheduleJob> getScheduleJobs(@Param("enprNo") String enprNo);
    int getCenterId(@Param("centerNo") String centerNo, @Param("enprNo") String enprNo);
    int deleteScheduleJob(@Param("id") int id);
    int addScheduleJob(ScheduleJob scheduleJob);
}
