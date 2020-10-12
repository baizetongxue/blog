package com.sc.service.impl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.sc.dao.BlogRepository;
import com.sc.exception.NotFoundIndexException;
import com.sc.pojo.Blog;
import com.sc.pojo.BlogAndTag;
import com.sc.pojo.Tag;
import com.sc.service.BlogService;
import com.sc.util.MarkdownUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import java.util.List;

/**
 * Created by BaiZe schoolmate on 2020/5/11 15:10.
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;


    @Override
    public Blog getBlogById(Long id) {
        return blogRepository.getBlogById(id);
    }

    @Override
    public List<Blog> listBlogs() {
        return blogRepository.listBlogs();
    }

    @Transactional
    @Override
    public int saveBlog(Blog blog) {
            blog.setCreateTime(LocalDateTime.now());
            blog.setUpdateTime(LocalDateTime.now());
            blog.setViews(0);
            int i = blogRepository.saveBlog(blog);
            if ( i < 0){
                return 0;
            }
            else {
                Long id = blog.getId();
                System.out.println(id);
                List<Tag> tags = blog.getTags();
                for (Tag tag : tags) {
                    blogRepository.saveBlogAndTag(new BlogAndTag(tag.getId(),id));
                }
                return 1;
            }
    }

    @Transactional
    @Override
    public int updateBlog(Blog blog){
        Blog blogFlag = blogRepository.getBlogById(blog.getId());
        if (null ==blogFlag){
            throw new NotFoundIndexException("该博客不存在");
        }
        BeanUtils.copyProperties(blog,blogFlag);
        blogFlag.setUpdateTime(LocalDateTime.now());
        List<Tag> tags = blog.getTags();
        blogRepository.deleteBlogAndTag(blog.getId());
        for (Tag tag : tags) {
            blogRepository.saveBlogAndTag(new BlogAndTag(tag.getId(),blog.getId()));
        }
        return blogRepository.updateBlog(blogFlag);
    }
    @Transactional
    @Override
    public int deleteBlog(Long id) {
        blogRepository.deleteBlogAndTag(id);
        return blogRepository.deleteBlog(id);
    }

    @Transactional
    @Override
    public List<Blog> conditionalSearchBlogs(Blog blog) {
        return blogRepository.conditionalSearchBlogs(blog);
    }

    @Override
    public List<Blog> getIndexBlog() {
        return blogRepository.getIndexBlog();
    }
    @Override
    public List<Blog> getRecommendBlogs(){
        return blogRepository.getRecommendBlogs();
    };

    @Override
    public List<Blog> getSearchBlogs(String query) {
        return blogRepository.getSearchBlogs(query);
    }

    @Override
    public Blog getDetailedBlogById(Long id) {
        Blog blog = blogRepository.getDetailedBlogById(id);
        if (null == blog){
            throw new NotFoundIndexException("该博客不存在");
        }
        Blog duplicateBlog = new Blog();
        BeanUtils.copyProperties(blog,duplicateBlog);
        String content = duplicateBlog.getContent();
        duplicateBlog.setContent( MarkdownUtils.markdownToHtmlExtensions(content));
        return duplicateBlog;
    }

    @Override
    public List<Blog> getBlogsByTypeId(Long id) {
        return blogRepository.getBlogsByTypeId(id);
    }

    @Override
    public List<Blog> getBlogsByTagId(Long id) {
        return blogRepository.getBlogsByTagId(id) ;
    }
}
