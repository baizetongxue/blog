package com.sc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Created by BaiZe schoolmate on 2020/5/10 14:15.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type {

    private Long id;
    @NotBlank(message = "分类名称不能为空")
    private String name;
    //type 1-->n blog
    private List<Blog> blogs;
}
