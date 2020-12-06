package com.example.demo.common.lang.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/*
 *
 *@author:zzf
 *@time:2020-12-04
 *
 */
@Data
public class LoginDto implements Serializable {
    /**
     * 用户名
     */
    @NotBlank(message="用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message="密码不能为空")
    private String password;

    private Integer status;
}
