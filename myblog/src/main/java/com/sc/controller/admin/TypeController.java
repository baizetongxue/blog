package com.sc.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sc.pojo.Type;
import com.sc.service.TypeService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

/**
 * Created by BaiZe schoolmate on 2020/5/10 21:51.
 */

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(Model model,
                        @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,5);
        List<Type> types = typeService.getTypes();
        PageInfo<Type> pageInfo = new PageInfo<>(types);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/type-input";
    }


    //增加分类
    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult result,
                       RedirectAttributes attributes){
       if (null != typeService.getByName(type.getName())){
           result.rejectValue("name","nameError","官人,分类已存在哦~");
       }
        if (result.hasErrors()){
            return "admin/type-input";
        }
        int i = typeService.SaveType(type);
        if (i>0){
            //新增成功
            attributes.addFlashAttribute("message","官人,你好棒~");
        }else {
            //失败
            attributes.addFlashAttribute("message","官人,这次不太行哦~");
        }
        return "redirect:/admin/types";
    }

    //修改分类的页面回显
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable("id") Long id, Model model){
        model.addAttribute("type",typeService.getById(id));
        return "admin/type-input";
    }

    //修改分类
    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type,BindingResult result,@PathVariable("id") Long id,
                           RedirectAttributes attributes) throws NotFoundException {
        if (null != typeService.getByName(type.getName())){
            result.rejectValue("name","nameError","官人,分类已存在哦~");
        }
        System.out.println(id);
        if (result.hasErrors()){
            return "admin/type-input";
        }
        int i = typeService.updateType(type);
        if (i>0){
            //新增成功
            attributes.addFlashAttribute("message","官人,更新成功了哦~");
        }else {
            //失败
            attributes.addFlashAttribute("message","官人,更新失败了哦~");
        }
        return "redirect:/admin/types";
    }
    @GetMapping("/types/{id}/delete")
    public String TypeDelete(@PathVariable("id") Long id,RedirectAttributes attributes){
        int i = typeService.deleteById(id);
        if (i>0){
            //新增成功
            attributes.addFlashAttribute("message","官人,删除成功了哦~");
        }else {
            //失败
            attributes.addFlashAttribute("message","官人,删除失败了哦~");
        }
        return "redirect:/admin/types";
    }

}
