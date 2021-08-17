package com.pzy.controller;


import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pzy.dto.UserDto;
import com.pzy.entity.User;
import com.pzy.service.EmailService;
import com.pzy.service.UserService;
import com.pzy.util.CodeGenerateUtil;
import com.pzy.util.JwtUtil;
import com.pzy.util.Result;
import com.pzy.util.SnowFlakeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author nice
 * @since 2021-07-07
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired(required = false)
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    //用于存储用户及code码
    private static ConcurrentMap<String, String> userMap = new ConcurrentHashMap<>();

    @GetMapping("/getMap")
    public Result getMap(){
        return Result.succ(userMap);
    }

    /**
     * @author : pangzy
     * @date : 2021/7/3 14:02
     * @return : java.lang.Object
     * 发送邮箱验证码
     */
    @PostMapping(value = "/sendCode")
    public Result sendEmail(@RequestParam("email") String receiver) {
        User user = userService.getOne(new QueryWrapper<User>().eq("username", receiver));
        if(user!=null){
            return Result.fail("该邮箱已注册");
        }else {
            String code = CodeGenerateUtil.generateVerCode();
            userMap.put(receiver, code);
            try {
                emailService.sendEmailVerCode(receiver, code);
                return Result.succ("code发送成功");
            } catch (Exception e) {
                return Result.fail("验证码发送失败");
            }
        }
    }

    /**
     * @author : pangzy
     * @date : 2021/7/3 14:02
     * @return : com.pzy.util.Result
     * 用户注册传来得code码比对，正确的话则注册成功，否则验证码错误，
     * 并判断用户是否存在，
     */
    @PostMapping("/register")
    public Result register(@RequestParam("username") String username
            ,@RequestParam("code") String code
            ,@RequestParam("nickname") String nickname
            ,@RequestParam("password") String password,User user){
        //将用户用户名跟验证码作为key，value存入map集合
        Set sefKey = userMap.keySet();
        Iterator iterator = sefKey.iterator();
        boolean istrue = false;
        //遍历查找该用户的key然后比对code码是否一致
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            String value = userMap.get(key);
            if (key.equals(username) && value.equals(code)){
                istrue = true;
                break;
            }else{
                istrue=false;
            }
        }
        if (istrue){
            // 使用雪花算法生成用户唯一id
            user.setId(SnowFlakeUtil.getId());
            user.setUsername(username);
            //给用户的密码进行加密
            user.setPassword(SecureUtil.md5(password));
            user.setNickname(nickname);
            user.setEmail(username);
            //默认背景图片
            user.setBackground("http://p8.qhimg.com/bdm/1024_768_85/t01a9e376952238ea53.jpg");
            // 判断邮箱是否为qq邮箱，设置用户头像为qq头像
            if (username.trim().toLowerCase().contains("@qq.com")){
                String regEx = "[^0-9]";
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(username);
                user.setAvatar("http://q1.qlogo.cn/g?b=qq&nk="+m.replaceAll("").trim()+"&s=100");
            }
            //调用service保存用户
            boolean save = userService.save(user);
            if(save){
                return Result.succ("注册成功");
            }else {
                return Result.fail("注册失败");
            }
        }else{
            return Result.fail("验证码错误");
        }
    }

    /**
     * @author : pangzy
     * @date : 2021/7/6 18:22
     * @return : com.pzy.util.Result
     * 用户登录，判断用户是否已存在及密码是否正确
     */
    @PostMapping("/login")
    public Result login(@RequestParam("username") String username ,@RequestParam("password") String password,HttpServletResponse response){
        User user = userService.getOne(new QueryWrapper<User>().eq("username", username));
        if (user != null){
            if(!user.getPassword().equals(SecureUtil.md5(password))){
                return Result.fail("密码不正确");
            }else {
                UserDto userDto = new UserDto();
                //将user的复值给UserDto一样的字段
                BeanUtils.copyProperties(user,userDto);
//                System.out.println(JSON);
                String jwt = jwtUtil.generateToken(user);
                response.setHeader("Authorization",jwt);
                response.setHeader("Access-control-Expose-Headers","Authorization");
//                //UserDto 用来存储返回给前端的部分信息，不包含重要信息

                return Result.succ(200,"登录成功",jwt);
            }
        }else{
            return Result.fail("该用户不存在");
        }
    }


/**
 * @author : pangzy
 * @date : 2021/7/8 18:03
 * @return : com.pzy.util.Result
 * 退出登录
 */
    //该注解作用为，必须登录才能进行访问
    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout(){
        SecurityUtils.getSubject().logout();
        return Result.succ(null);
    }


    @GetMapping("/getUser")
    public Result getUser(@RequestParam("id") String id){
        System.out.println(id);
        User user = userService.getById(id);
        System.out.println(user);
        return Result.succ(user);
    }


    @PostMapping("/save")
    public Result save(@Validated @RequestBody User user){
        return Result.succ(user);
    }

}
