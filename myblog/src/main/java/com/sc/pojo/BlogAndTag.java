package com.sc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by BaiZe schoolmate on 2020/5/12 21:14.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogAndTag {

    private int id;
    private Long tagId;
    private Long blogId;

    public BlogAndTag(Long tagId, Long blogId) {
        this.tagId = tagId;
        this.blogId = blogId;
    }
}
