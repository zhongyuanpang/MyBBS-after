package com.pzy.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
@TableName("tb_user")
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户登录账号
     */
    
    private String username;

    /**
     * 用户登录密码
     */
    private String password;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户创建时间
     * fill = FieldFill.INSERT 自动填充插入时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 用户更新时间
     * fill = FieldFill.INSERT_UPDATE   自动填充更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 用户性别
     */
    private LocalDateTime sex;

    /**
     * 用户手机号
     */
    private Long phone;

    /**
     * 个人签名
     */
    private String signature;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户状态
     */
    private Integer status;

    /**
     * 用户背景图片
     */
    private String background;
}
