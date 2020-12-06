package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.common.lang.Result;
import com.example.demo.entity.DzUser;

/*
 *
 *@author:zzf
 *@time:2020-12-05
 *
 */
public interface RegistService {
    Result regist(DzUser user);
}
