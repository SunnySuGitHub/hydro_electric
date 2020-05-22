package com.hust.hydroelectric_backend.Dao;

import com.github.pagehelper.Page;
import com.hust.hydroelectric_backend.Entity.Areas.Community;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/19 16:33
 */
@Repository
public interface CommunityMapper {

    int addCommunity(Community community);

    int delCommunity(@Param("cId") int id);

    Community getCommunity(@Param("cId") int id);

    int uptCommunity(Community community);

    Page<Community> communityList(@Param("enprNo") String enprNo);

}
