package com.hust.hydroelectric_backend.Service.AreaServices;

import com.hust.hydroelectric_backend.Dao.BlockMapper;
import com.hust.hydroelectric_backend.Entity.Areas.Block;
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
        return Result.success(blockMapper.findAllByCid(cid));
    }

    public ResultData getBlock(int bid){
        return Result.success(blockMapper.getBlock(bid));
    }

    public ResultData delBlock(int bid){
        return Result.success(blockMapper.delBlock(bid));
    }

    public ResultData uptBlock(Block block){
        return Result.success(blockMapper.uptBlock(block));
    }
}
