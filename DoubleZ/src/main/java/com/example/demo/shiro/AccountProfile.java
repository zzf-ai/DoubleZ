package com.example.demo.shiro;

import lombok.Data;

import java.io.Serializable;

/*
 *
 *@author:zzf
 *@time:2020-12-04
 *
 */
@Data
public class AccountProfile implements Serializable {

    private Integer id;

    /**
     * 角色编号
     */
    private Integer roleId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    //private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 账户状态
     */
    private Integer status;


}
