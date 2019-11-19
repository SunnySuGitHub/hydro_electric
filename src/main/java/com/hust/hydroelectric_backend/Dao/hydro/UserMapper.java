package com.hust.hydroelectric_backend.Dao.hydro;

import com.hust.hydroelectric_backend.Entity.User;
import com.hust.hydroelectric_backend.Ex.UserInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/18 15:34
 */
@Repository
public interface UserMapper {
    List<User> findAllUsers(@Param("enprNo") String enprNo);
    User findByUid(@Param("uid") int uid);
    Set<String> findAllUserNo(String enprNo);
    int saveUser(User user);
    int findUidByUnoAndEnprno(@Param("uNo")String uNo,@Param("enprNo")String enprNo);
    int delUserByUid(@Param("uid") int uid);
    int uptUser(User user);
    List<UserInfoVo>
}
