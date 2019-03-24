package com.spring.mybatis.springbootdemo.service.impl;

import com.spring.mybatis.springbootdemo.entity.SysUser;
import com.spring.mybatis.springbootdemo.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserServiceImplTest {
    @Resource
    private SysUserService sysUserService;
    @Test
    public void selectAll() {
        List<SysUser> userList=sysUserService.selectAll();
        userList.forEach(sysUser -> System.out.println(sysUser));
    }

    @Test
    public void getOne() {
        SysUser sysUser=sysUserService.getOne(2L);
        System.out.println(sysUser);
    }

    @Test
    public void delete() {
        sysUserService.delete(2L);
    }

    @Test
    public void insert() {
        SysUser sysUser=new SysUser();
        sysUser.setMobile((long) 14858258);
        sysUser.setPassword("123wjl");
        sysUser.setUsername("王杰磊");
        sysUser.setAvatar("123.jpg");
        sysUserService.insert(sysUser);
    }

    @Test
    public void update() {
        SysUser sysUser=sysUserService.getOne(5L);
        sysUser.setMobile((long) 1487258);
        sysUser.setPassword("1234wjl");
        sysUser.setUsername("王磊");
        sysUser.setAvatar("130.jpg");
        sysUserService.update(sysUser);
    }
}