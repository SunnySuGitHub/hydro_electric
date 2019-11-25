package com.hust.hydroelectric_backend.Dao;

import com.hust.hydroelectric_backend.Entity.Areas.Block;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/19 12:15
 */
@Repository
public interface BlockMapper {
    Block findByBlockNameAndCid(@Param("blockName") String blockName, @Param("cId") int communityId);
    int saveBlock(Block block);
    List<Block> findAllByCid(@Param("cid") int cid);
}
