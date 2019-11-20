package com.hust.hydroelectric_backend.Service;

import com.hust.hydroelectric_backend.Dao.hydro.BlockMapper;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/20 21:11
 */
@Service
public class BlockService {

    @Resource
    BlockMapper blockMapper;

    public ResultData getBlockByCid(int cid){
        if(cid == -1){
            return Result.error(HttpStatus.BAD_REQUEST, "参数错误");
        } else {
            return Result.success(blockMapper.findAllByCid(cid));
        }
    }
}
