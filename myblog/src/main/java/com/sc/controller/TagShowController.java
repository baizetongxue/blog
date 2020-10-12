package com.sc.controller;

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
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by BaiZe schoolmate on 2020/5/18 21:20.
 */
@Controller
public class TagShowController {

    @Resource
    private TagService tagService;

    @Resource
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tags(@PathVariable Long id, Model model,
                        @RequestParam(defaultValue = "1", value = "pageNum")Integer pageNum){
        PageHelper.startPage(pageNum, 10000);
        List<Tag> tags = tagService.getRightSidebarTags();
        if (-1 == id){
            id  = tags.get(0).getId();
        }
        List<Blog> blogs = blogService.getBlogsByTagId(id);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("tags",tags);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("activeTag",id);
        return "tags";
    }
}
