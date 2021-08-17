package com.pzy.shiro;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pzy.dto.UserDto;
import com.pzy.entity.User;
import com.pzy.service.UserService;
import com.pzy.util.JwtUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @Author Nice
 * @Date 2021/7/8 16:34
 */
@Slf4j
@Component
public class AccountRealm extends AuthorizingRealm {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @SneakyThrows
    @Override
    /**
     * @author : pangzy
     * @date : 2021/7/8 17:29
     * @return : org.apache.shiro.authc.AuthenticationInfo
     * 登录查询，根据用户查询，如果存在则返回toke用户信息，不存在给予提示
     */
    @SuppressWarnings("unchecked")
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) token;
        log.info("jwt----------------->{}", jwtToken);
        String userId = jwtUtil.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();
        System.out.println(userId);
        String s = userId;
        JSONObject jsonObject = JSONObject.parseObject(s);
        String r = jsonObject.getString("id");
        System.out.println(r);

        User user = userService.getById(Long.valueOf(r));
        System.out.println(user+"-------------------------{ user }");
        if(user == null){
            throw new UnknownAccountException("账户不存在");
        }
//
//        if(user.getStatus() == -1){
//            throw new LockedAccountException("账户已被锁定");
//        }

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user,userDto);
        System.out.println(new SimpleAuthenticationInfo(userDto,jwtToken.getCredentials(),getName()));
        return new SimpleAuthenticationInfo(user,jwtToken.getCredentials(),getName());
    }
}
