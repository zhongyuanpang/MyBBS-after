package com.pzy.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author Nice
 * @Date 2021/7/8 11:21
 */
@Data
public class UserDto implements Serializable {

    /**
     * 用户登录账号
     */
    private Long id;

    /**
     * 用户登录账号
     */
    private String username;

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
     */
    private String createTime;

    /**
     * 用户更新时间
     */
    private String updateTime;

    /**
     * 用户性别
     */
    private String sex;

    /**
     * 个人签名
     */
    private String signature;

    /**
     * 邮箱
     */
    private String email;
}
