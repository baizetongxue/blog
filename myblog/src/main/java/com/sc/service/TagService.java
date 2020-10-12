package com.sc.service;

import com.sc.pojo.Tag;
import com.sc.pojo.Type;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by BaiZe schoolmate on 2020/5/11 14:26.
 */
public interface TagService {

    List<Tag> getTags();

    //
    List<Tag> getAllTag();

    int deleteById(@Param("id")Long id);

    //保存tag
    int saveTag(Tag tag);

    //通过id  name 查询type
    Tag getById(Long id);
    Tag getByName(String name);

    int updateTag(Tag tag);

    List<Tag> getTagByTagIds(String tagIds);


    List<Tag> getRightSidebarTags();
}
