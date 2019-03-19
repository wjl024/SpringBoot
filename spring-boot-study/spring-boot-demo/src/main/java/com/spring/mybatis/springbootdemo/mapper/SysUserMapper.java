package com.spring.mybatis.springbootdemo.mapper;

import com.spring.mybatis.springbootdemo.entity.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SysUserMapper {
    @Results({
            @Result(property = "userId",column = "user_id"),
            @Result(property = "mobile",column = "mobile"),
            @Result(property = "password",column = "password"),
            @Result(property = "username",column = "username"),
            @Result(property = "avatar",column = "avatar"),
    })
    @Select("SELECT * FROM t_sys_user ")
    List<SysUser> selectAll();

    @Results({
            @Result(property = "userId",column = "user_id"),
            @Result(property = "mobile",column = "mobile"),
            @Result(property = "password",column = "password"),
            @Result(property = "username",column = "username"),
            @Result(property = "avatar",column = "avatar"),
    })
    @Select("SELECT * FROM t_sys_user WHERE user_id=#{userId} ")
    SysUser getOne(long userId);

    @Delete("DELETE FROM t_sys_user WHERE user_id=#{userId} ")
    void delete(long userId);

    @Insert("INSERT INTO t_sys_user(mobile,password,username,avatar)VALUES(#{mobile},#{password},#{username},#{avatar})")
    void insert(SysUser sysUser);

    @Update("UPDATE t_sys_user SET mobile=#{mobile},password=#{password},username=#{username},avatar=#{avatar}")
    void update(SysUser sysUser);
}
