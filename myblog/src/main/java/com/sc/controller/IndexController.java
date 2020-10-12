package com.sc.controller;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sc.pojo.Blog;
import com.sc.pojo.Tag;
import com.sc.pojo.Type;
import com.sc.service.BlogService;
import com.sc.service.TagService;
import com.sc.service.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class IndexController {

    @Resource
    private BlogService blogService;
    @Resource
    private TypeService typeService;
    @Resource
    private TagService tagService;

    @GetMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "1", value = "pageNum")Integer pageNum) {
        PageHelper.startPage(pageNum, 8);
        List<Blog> blogs = blogService.getIndexBlog();
        List<Type> types = typeService.getRightSidebarTypes();
        List<Tag> tags = tagService.getRightSidebarTags();
        List<Blog> recommendBlogs = blogService.getRecommendBlogs();
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("tags", tags);
        model.addAttribute("types", types);
        model.addAttribute("recommendBlogs",recommendBlogs);
        return "index";
    }

    @PostMapping("/search")
    public String search(Model model, @RequestParam(defaultValue = "1", value = "pageNum")Integer pageNum,
                         @RequestParam("query") String query) {
        PageHelper.startPage(pageNum, 5);
        List<Blog> searchBlog = blogService.getSearchBlogs(query);
        PageInfo pageInfo = new PageInfo(searchBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }


    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model){

        model.addAttribute("blog",blogService.getDetailedBlogById(id));
        return "blog";
    }

}
