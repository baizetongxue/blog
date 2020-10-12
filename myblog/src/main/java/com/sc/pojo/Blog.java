package com.sc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by BaiZe schoolmate on 2020/5/10 14:05.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    /**
     *     id;                           id
     *     title;                        标题
     *     content;                      内容
     *     firstPicture;                 首图
     *     flag;                         标记
     *     views;                        浏览次数
     *     appreciation;                 赞赏开启
     *     shareStatement;               版权开启
     *     commentabled;                 评论开启
     *     published;                    是否发布
     *     recommend;                    是否推荐
     *     createTime;                   创建时间
     *     updateTime;                   更新时间
     */
    private Long id;
    private String title;
    private String content;
    private String firstPicture;
    private String flag;
    private Integer views;
    private boolean appreciation;
    private boolean shareStatement;
    private boolean commentabled;
    private boolean published;
    private boolean recommend;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;


    private Long typeId;
    private Long userId;
    //获取标签的id
    private String tagId;

    private String description;

    //type 1-->n blog
    private Type type ;
    //tag n<-->m blog
    private List<Tag> tags;
    // user 1 --> blog
    private User user ;
    //blog 1 -->n comment
    private List<Comment> comments = new ArrayList<>();

    public void init(){
        this.tagId = tagsToIds(this.getTags());
    }

    //将tags集合转换为tagId字符串形式：“1,2,3”,用于编辑博客时显示博客的tag
    private String tagsToIds(List<Tag> tags){
        if(!tags.isEmpty()){
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for(Tag tag: tags){
                if(flag){
                    ids.append(",");
                }else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        }else {
            return tagId;
        }
    }
}
