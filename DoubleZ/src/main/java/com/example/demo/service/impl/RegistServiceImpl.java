package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.lang.Result;
import com.example.demo.entity.DzUser;
import com.example.demo.mapper.DzUserMapper;
import com.example.demo.service.RegistService;
import com.example.demo.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Date;

/*
 *
 *@author:zzf
 *@time:2020-12-05
 *
 */
@Service
public class RegistServiceImpl implements RegistService {
    Integer status=-1;//未激活

    @Resource
    private DzUserMapper userMapper;

    @Override
    public Result regist(DzUser dzUser) {
        dzUser.setRoleId(1);
        dzUser.setStatus(status);
        dzUser.setCreateDate(new Date());
        DzUser user = userMapper.selectOne(new QueryWrapper<DzUser>().eq("username", dzUser.getUsername()));
        if (user!=null){
            return Result.error("用户名已注册");
        }

        user = userMapper.selectOne(new QueryWrapper<DzUser>().eq("email", dzUser.getEmail()));
        if(user!=null){
            return Result.error("邮箱已注册");
        }

        user = userMapper.selectOne(new QueryWrapper<DzUser>().eq("phone", dzUser.getPhone()));
        if(user!=null){
            return Result.error("手机号已注册");
        }
        userMapper.insert(dzUser);

        dzUser=userMapper.selectOne(new QueryWrapper<DzUser>().eq("username", dzUser.getUsername()));

        try{
            new MailUtil(dzUser.getEmail(),dzUser.getId()).sendEmail();
        }catch (Exception e){
            return Result.error("邮件发送失败");
        }
        return Result.success("邮件发送成功");
    }
}
