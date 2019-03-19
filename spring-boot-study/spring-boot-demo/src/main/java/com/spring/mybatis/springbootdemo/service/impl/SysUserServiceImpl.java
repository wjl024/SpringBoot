package com.spring.mybatis.springbootdemo.service.impl;

import com.spring.mybatis.springbootdemo.entity.SysUser;
import com.spring.mybatis.springbootdemo.mapper.SysUserMapper;
import com.spring.mybatis.springbootdemo.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> selectAll() {
        return sysUserMapper.selectAll();
    }

    @Override
    public SysUser getOne(long userId) {
        return sysUserMapper.getOne(userId);
    }

    @Override
    public void delete(long userId) {
        sysUserMapper.delete(userId);
    }

    @Override
    public SysUser insert(SysUser sysUser) {
        sysUserMapper.insert(sysUser);
        return sysUser;
    }

    @Override
    public void update(SysUser sysUser) {
        sysUserMapper.update(sysUser);
    }
}
