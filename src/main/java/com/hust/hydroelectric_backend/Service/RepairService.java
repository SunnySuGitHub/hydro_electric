package com.hust.hydroelectric_backend.Service;

import com.hust.hydroelectric_backend.Dao.RepairMapper;
import com.hust.hydroelectric_backend.Entity.Repair;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/20 10:57
 */
@Service
public class RepairService {

    @Resource
    RepairMapper repairMapper;

    public ResultData add(Repair repair){
        repair.setSubmitTime(System.currentTimeMillis()/1000);
        return Result.success(repairMapper.save(repair));
    }

    public ResultData list(int cid, int state){
        return Result.success(repairMapper.list(cid, state));
    }

    public ResultData upt(Repair repair){
        if(repair.getRepairId() == null){
            return Result.error(HttpStatus.BAD_REQUEST, "参数不规范");
        } else {
            return Result.success(repairMapper.uptRepair(repair));
        }
    }
}