package com.sc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sc.pojo.Blog;
import com.sc.pojo.Type;
import com.sc.service.BlogService;
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
public class TypeShowController {

    @Resource
    private TypeService typeService;

    @Resource
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PathVariable Long id, Model model,
                        @RequestParam(defaultValue = "1", value = "pageNum")Integer pageNum){
        PageHelper.startPage(pageNum, 10000);
        List<Type> types = typeService.getRightSidebarTypes();
        if (-1 == id){
            id  = types.get(0).getId();
        }
        List<Blog> blogs = blogService.getBlogsByTypeId(id);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("types",types);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("activeType",id);
        return "types";
    }
}
