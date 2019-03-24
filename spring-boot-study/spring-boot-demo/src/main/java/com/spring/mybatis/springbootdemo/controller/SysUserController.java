package com.spring.mybatis.springbootdemo.controller;

import com.spring.mybatis.springbootdemo.entity.SysUser;
import com.spring.mybatis.springbootdemo.service.SysUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin("http://localhost:80")
public class SysUserController {
    @Resource
    private SysUserService sysUserService;

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public List<SysUser> selectAll(){
        return sysUserService.selectAll();
    }

    @RequestMapping(value = "/users/{id}",method = RequestMethod.GET)
    public SysUser getOne(@PathVariable("id") long id){
        return sysUserService.getOne(id);
    }

    @RequestMapping(value = "/users/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") long id){
        sysUserService.delete(id);
    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public void addUser(@RequestBody SysUser sysUser){
        sysUserService.insert(sysUser);
    }

    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public void updateUser(@RequestBody SysUser sysUser){
        sysUserService.update(sysUser);
    }
}
