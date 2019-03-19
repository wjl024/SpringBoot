package com.spring.mybatis.springbootdemo.service;

import com.spring.mybatis.springbootdemo.entity.SysUser;

import java.util.List;

public interface SysUserService {
    List<SysUser> selectAll();
    SysUser getOne(long userId);
    void delete(long userId);
    SysUser insert(SysUser sysUser);
    void update(SysUser sysUser);
}
