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
@TableName("tb_user_diary")
public class UserDiary implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日记id
     */
    private Integer id;

    /**
     * 日记内容
     */
    private String content;

    /**
     * 日记标题
     */
    private String title;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 日记展示封面
     */
    private String firstPicture;

    /**
     * 用户id
     */
    private String userId;


}
