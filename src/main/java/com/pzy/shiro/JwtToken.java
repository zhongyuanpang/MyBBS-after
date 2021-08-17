package com.pzy.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Author Nice
 * @Date 2021/7/8 16:48
 */

public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
