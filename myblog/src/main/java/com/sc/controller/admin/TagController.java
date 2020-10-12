package com.sc.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sc.pojo.Tag;
import com.sc.service.TagService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by BaiZe schoolmate on 2020/5/11 14:37.
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;


    @GetMapping("/tags")
    public String tags(Model model,
                        @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,5);
        List<Tag> tags = tagService.getTags();
        PageInfo<Tag> pageInfo = new PageInfo<>(tags);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tag-input";
    }


    //增加分类
    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult result,
                       RedirectAttributes attributes){
        if (null != tagService.getByName(tag.getName())){
            result.rejectValue("name","nameError","官人,标签已存在哦~");
        }
        if (result.hasErrors()){
            return "admin/tag-input";
        }
        int i = tagService.saveTag(tag);
        if (i>0){
            //新增成功
            attributes.addFlashAttribute("message","官人,你好棒~");
        }else {
            //失败
            attributes.addFlashAttribute("message","官人,这次不太行哦~");
        }
        return "redirect:/admin/tags";
    }

    //修改分类的页面回显
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable("id") Long id, Model model){
        model.addAttribute("tag",tagService.getById(id));
        return "admin/tag-input";
    }

    //修改分类
    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag tag,BindingResult result,@PathVariable("id") Long id,
                           RedirectAttributes attributes) throws NotFoundException {
        if (null != tagService.getByName(tag.getName())){
            result.rejectValue("name","nameError","官人,分类已存在哦~");
        }
        System.out.println(id);
        if (result.hasErrors()){
            return "admin/tag-input";
        }
        int i = tagService.updateTag(tag);
        if (i>0){
            //新增成功
            attributes.addFlashAttribute("message","官人,更新成功了哦~");
        }else {
            //失败
            attributes.addFlashAttribute("message","官人,更新失败了哦~");
        }
        return "redirect:/admin/tags";
    }
    @GetMapping("/tags/{id}/delete")
    public String TagDelete(@PathVariable("id") Long id, RedirectAttributes attributes){
        int i = tagService.deleteById(id);
        if (i>0){
            //新增成功
            attributes.addFlashAttribute("message","官人,删除成功了哦~");
        }else {
            //失败
            attributes.addFlashAttribute("message","官人,删除失败了哦~");
        }
        return "redirect:/admin/tags";
    }


}
