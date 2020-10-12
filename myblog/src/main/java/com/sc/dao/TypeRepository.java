package com.sc.dao;

import com.sc.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by BaiZe schoolmate on 2020/5/10 19:30.
 */

@Mapper
@Repository
public interface TypeRepository {

    List<Type> getTypes();

    //貌似没用，先放这里
    List<Type> getAllTypes();

    int deleteById(@Param("id")long id);

    //保存type
    int saveType(Type type);

    //通过id  name 查询type
    Type getById(@Param("id")long id);
    Type getByName(@Param("name")String name);

    int updateType(Type type);

    List<Type> getRightSidebarTypes();

}
