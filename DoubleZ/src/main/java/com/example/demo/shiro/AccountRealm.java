package com.example.demo.shiro;

import cn.hutool.core.bean.BeanUtil;
import com.example.demo.entity.DzUser;
import com.example.demo.service.DzUserService;
import com.example.demo.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 *
 *@author:zzf
 *@time:2020-12-04
 *
 */
@Slf4j
@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    DzUserService dzUserService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println(11111);
        JwtToken jwtToken= (JwtToken) authenticationToken;
        log.info("jwt------------------>{}",jwtToken);
        System.out.println(jwtToken);
        String userId = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();
        System.out.println(userId);
        DzUser dzUser = dzUserService.getById(Integer.valueOf(userId));
        if(dzUser==null){
            throw new UnknownAccountException("账户不存在");
        }
        if(dzUser.getStatus()==-1){
            throw new LockedAccountException("账户被锁定");
        }
        AccountProfile accountProfile=new AccountProfile();
        BeanUtil.copyProperties(dzUser,accountProfile);

        return new SimpleAuthenticationInfo(accountProfile,jwtToken.getCredentials(),getName());
    }
}
