package com.sc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BaiZe schoolmate on 2020/5/10 14:17.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    private Long id;
    @NotBlank(message = "标签名称不能为空")
    private String name;

    //tag n<-->m blog
    private List<Blog> blogs;
}
