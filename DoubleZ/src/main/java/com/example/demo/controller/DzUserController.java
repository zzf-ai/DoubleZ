package com.example.demo.controller;


import com.example.demo.common.lang.Result;
import com.example.demo.entity.DzUser;
import com.example.demo.service.DzUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzf
 * @since 2020-12-04
 */
@RestController
@RequestMapping("/dz-user")
public class DzUserController {

    @Autowired
    DzUserService dzUserService;


    @RequiresAuthentication
    @GetMapping("/index")
    public Result index(HttpServletResponse response){
        System.out.println(response.getHeader("Authorization"));
        System.out.println(SecurityUtils.getSubject().getPrincipal());
        System.out.println(1111);
        DzUser user = dzUserService.getById(1);
        return Result.success(user);
    }

    public Result save(@Validated @RequestBody DzUser user){
        return Result.success(user);
    }
}
