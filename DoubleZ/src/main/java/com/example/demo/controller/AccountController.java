package com.example.demo.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.lang.Result;
import com.example.demo.common.lang.dto.LoginDto;
import com.example.demo.common.lang.dto.RegisterDto;
import com.example.demo.entity.DzUser;
import com.example.demo.service.DzUserService;
import com.example.demo.service.RegistService;
import com.example.demo.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 *
 *@author:zzf
 *@time:2020-12-04
 *
 */
@RestController
public class AccountController {
    @Autowired
    DzUserService userService;

    @Autowired
    RegistService registService;

    @Autowired
    JwtUtils jwtUtils;

    //登录
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response){
        DzUser user = userService.getOne(new QueryWrapper<DzUser>().eq("username", loginDto.getUsername()));
        Assert.notNull(user,"用户不存在");

        //System.out.println(SecureUtil.md5(loginDto.getPassword()));
        if(!user.getPassword().equals(loginDto.getPassword())){
            System.out.println("fu");
            return Result.error("密码不正确");
        }

        if(user.getStatus()==-1){
            return Result.error("账号未激活");
        }

        //生成jwt
        String jwt = jwtUtils.generateToken(user.getId());

        System.out.println(jwt);
        response.setHeader("Authorization",jwt);
        response.setHeader("zzf",jwt);

        //System.out.println(request.getHeader("Authorization"));
        response.setHeader("Access-control-Expose-Headers","Authorization");

        return Result.success(MapUtil.builder()
        .put("id",user.getId())
        .put("roleId",user.getRoleId())
        .put("username",user.getUsername())
        .put("status",user.getStatus())
        .put("email",user.getEmail())
        .put("phone",user.getPhone())
        .map()
        );

    }

    //退出
    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout(){
        SecurityUtils.getSubject().logout();
        return Result.success(null);
    }

    @PostMapping("/register")
    public Result register(@Validated @RequestBody DzUser dzUser){

        /*DzUser user = userService.getOne(new QueryWrapper<DzUser>().eq("username", dzUser.getUsername()));
        if (user!=null){
            return Result.error("用户名已注册");
        }

        DzUser user2 = userService.getOne(new QueryWrapper<DzUser>().eq("email", dzUser.getEmail()));
        if(user2!=null){
            return Result.error("邮箱已注册");
        }

        DzUser user3 = userService.getOne(new QueryWrapper<DzUser>().eq("phone", dzUser.getPhone()));
        if(user3!=null){
            return Result.error("手机号已注册");
        }
        dzUser.setRoleId(1);
        userService.saveOrUpdate(dzUser);*/
        Result result = registService.regist(dzUser);
        if(result.getCode()==200) {
            return Result.success("注册成功");
        }else{
            return result;
        }
    }

    @GetMapping("/active")
    public void active(@RequestParam("id") Integer id,HttpServletResponse response){
        try{
            DzUser dzUser = userService.getById(id);
            dzUser.setStatus(0);
            userService.saveOrUpdate(dzUser);
            //return Result.success("账号已激活");
            response.sendRedirect("http://localhost:8080/login");
        }catch (Exception e){
            //return Result.error("账号未激活");
            e.printStackTrace();
        }
    }

}
