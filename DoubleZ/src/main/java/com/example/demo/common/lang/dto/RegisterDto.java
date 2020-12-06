package com.example.demo.common.lang.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/*
 *
 *@author:zzf
 *@time:2020-12-05
 *
 */
@Data
public class RegisterDto implements Serializable {

    /**
     * 用户名
     */
    @NotBlank(message="用户名不能为空")
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    @NotBlank(message="邮箱不能为空")
    @Email(message="邮箱格式不正确")
    private String email;

    /**
     * 手机号码
     */
    @NotBlank(message="邮箱不能为空")
    private String phone;
}
