package com.sc.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sc.constant.BlogConstant;
import com.sc.dao.BlogRepository;
import com.sc.pojo.Blog;
import com.sc.pojo.Tag;
import com.sc.pojo.User;
import com.sc.service.BlogService;
import com.sc.service.TagService;
import com.sc.service.TypeService;
import com.sc.service.impl.TagServiceImpl;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by BaiZe schoolmate on 2020/5/10 17:36.
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String blogs(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<Blog> blogs = blogService.listBlogs();
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("types", typeService.getAllTypes());
        model.addAttribute("pageInfo", pageInfo);
        return BlogConstant.LIST;
    }


    @GetMapping("/blogs/search")
    public String searchBlog(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<Blog> blogs = blogService.listBlogs();
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
        return BlogConstant.REDIRECT_LIST;
    }

    @PostMapping("/blogs/search")
    public String searchBlogPost(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, Blog blog) {
        PageHelper.startPage(pageNum, 5);
        List<Blog> blogs = blogService.conditionalSearchBlogs(blog);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/blogs :: blogList";
    }

    private void setTypeAndTag(Model model) {
        model.addAttribute("types", typeService.getTypes());
        model.addAttribute("tags", tagService.getTags());
    }

    @GetMapping("/blogs/input")
    public String inputBlog(Model model) {
        setTypeAndTag(model);
        model.addAttribute("blog", new Blog());
        return BlogConstant.INPUT;
    }

    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        Blog blog = blogService.getBlogById(id);
        blog.init();
        System.out.println("--------------------分割线-------------------");
        System.out.println(blog.getDescription());
        model.addAttribute("blog", blog);
        setTypeAndTag(model);
        return BlogConstant.INPUT;
    }

    @GetMapping("/blogs/{id}/delete")
    public String editDelete(@PathVariable Long id, RedirectAttributes attributes){
        int i = blogService.deleteBlog(id);
        if (i >0){
            attributes.addFlashAttribute("message","删除成功");

        }else{
            attributes.addFlashAttribute("message","删除失败");
        }
        return BlogConstant.REDIRECT_LIST;
    }


    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) throws NotFoundException {
        //设置user , userId
        blog.setUser((User) session.getAttribute("user"));
        blog.setUserId(blog.getUser().getId());

        //设置type typeId
        blog.setType(typeService.getById(blog.getType().getId()));
        blog.setTypeId(blog.getType().getId());
        //设置tags
        blog.setTags(tagService.getTagByTagIds(blog.getTagId()));
        blog.setUser((User) session.getAttribute("user"));

        if (null == blog.getId()) {
            //保存blog
            int i = blogService.saveBlog(blog);
            if (i > 0) {
                attributes.addFlashAttribute("message", "官人您好棒哦~");
            } else {
                attributes.addFlashAttribute("message", "官人这次没有成功哦~");
            }
        } else {

            int i = blogService.updateBlog(blog);
            if (i > 0) {
                attributes.addFlashAttribute("message", "官人更新成功了哦~");
            } else {
                attributes.addFlashAttribute("message", "官人这次没有成功哦~");
            }
        }

        return BlogConstant.REDIRECT_LIST;
    }

}
