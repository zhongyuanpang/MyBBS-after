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
@TableName("tb_user_follow")
public class UserFollow implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 用户
     */
    private String userId;

    /**
     * 关注的人的id
     */
    private String focusonId;

    /**
     * 关注的时间
     */
    private LocalDateTime createtime;

    /**
     * 粉丝id
     */
    private String followerId;


}
