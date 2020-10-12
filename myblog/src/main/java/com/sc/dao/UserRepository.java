package com.sc.dao;

import com.sc.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by BaiZe schoolmate on 2020/5/10 15:57.
 */
@Mapper
@Repository
public interface UserRepository {
    User findByUsernameAndPassword(@Param("username") String username,
                                   @Param("password")String  password);
}
