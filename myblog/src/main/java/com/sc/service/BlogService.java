package com.sc.service;

import com.sc.pojo.Blog;
import com.sc.pojo.Tag;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by BaiZe schoolmate on 2020/5/11 14:53.
 */
@Service
public interface BlogService {


    List<Blog> listBlogs();

    /**
     * c u r d
     */
    Blog getBlogById(@Param("id") Long id);

    int saveBlog(Blog blog);

    int updateBlog(Blog blog) throws NotFoundException;

    int deleteBlog(Long id);

    /**
     * -------------------------Dividing line--------------------------------
     *                          按照要求查询                                    */

    List<Blog> conditionalSearchBlogs(Blog blog);

    List<Blog> getIndexBlog();

    List<Blog> getRecommendBlogs();

    List<Blog> getSearchBlogs(String query);

    Blog getDetailedBlogById(Long id);

    List<Blog> getBlogsByTypeId(Long id);

    List<Blog> getBlogsByTagId(Long id);
}
