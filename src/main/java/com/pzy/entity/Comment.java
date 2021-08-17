package com.pzy.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author nice
 * @since 2021-07-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 该条评论的id
     */
    private Long id;

    /**
     * 评论人的昵称
     */
    private String nickname;

    /**
     * 评论人的头像
     */
    private String avatar;

    /**
     * 评论的内容
     */
    private String content;

    /**
     * 评论的时间
     */
    private LocalDateTime createTime;

    /**
     * 博客的id
     */
    private Long blogId;

    /**
     * 访客评论id
     */
    private Long visitorsCommentId;

    /**
     * 博客作者回复的id
     */
    private Integer userCommentId;


}
