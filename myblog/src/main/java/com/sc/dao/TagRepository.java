package com.sc.dao;

import com.sc.pojo.Blog;
import com.sc.pojo.Tag;
import com.sc.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by BaiZe schoolmate on 2020/5/11 14:17.
 */

@Mapper
@Repository
public interface TagRepository {

    List<Tag> getTags();


    List<Tag> getAllTags();

    int deleteById(@Param("id")Long id);

    //保存type
    int saveTag(Tag tag);

    //通过id  name 查询type
    Tag getById(@Param("id")Long id);
    Tag getByName(@Param("name")String name);

    int updateTag(Tag tag);

    List<Tag> getRightSidebarTags();

}
