package com.spring.mybatis.springbootdemo.entity;

import lombok.Data;

@Data
public class SysUser {
    private Long userId;
    private Long mobile;
    private String password;
    private String username;
    private String avatar;
}
