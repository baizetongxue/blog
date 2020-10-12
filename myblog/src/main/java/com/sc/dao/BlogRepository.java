package com.sc.dao;

import com.sc.pojo.Blog;
import com.sc.pojo.BlogAndTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by BaiZe schoolmate on 2020/5/11 15:10.
 */

@Mapper
@Repository
public interface BlogRepository {

    Blog getBlogById(@Param("id") Long id);

    List<Blog> listBlogs();

    int saveBlog(Blog blog);

    int updateBlog(Blog blog);

    int deleteBlog(@Param("id") Long id);

    List<Blog> conditionalSearchBlogs(Blog blog);

    int saveBlogAndTag(BlogAndTag blogAndTag);

    int deleteBlogAndTag(@Param("id") Long id);

    List<Blog> getIndexBlog();

    //拿到推荐博客
    List<Blog> getRecommendBlogs();

    List<Blog> getSearchBlogs(String query);

    List<Blog> getBlogsByTypeId(@Param("id")Long id);

    List<Blog> getBlogsByTagId(@Param("id") Long id);

    Blog getDetailedBlogById(@Param("id") Long id);



}
