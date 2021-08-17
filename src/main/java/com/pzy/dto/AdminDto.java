package com.pzy.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author nice
 * @since 2021-07-08
 */
public class AdminDto implements Serializable {

    /**
     * 登录用户名
     */
    private String username;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 性别
     */
    private String sex;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField("updateTime")
    private LocalDateTime updateTime;

    /**
     * 用户类型
     */
    private Integer type;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户状态
     */
    private Integer status;
}
