package com.sc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * Created by BaiZe schoolmate on 2020/5/10 14:20.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    private LocalDateTime createTime;

    private Long blogId;

    private Long parentCommentId;

    private long visitorId;

    private boolean adminComment;

}
