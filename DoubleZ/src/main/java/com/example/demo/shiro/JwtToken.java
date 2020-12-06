package com.example.demo.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/*
 *
 *@author:zzf
 *@time:2020-12-04
 *
 */
//自定义token，完成shiro的supports方法
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

