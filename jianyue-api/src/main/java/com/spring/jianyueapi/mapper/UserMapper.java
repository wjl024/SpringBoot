package com.spring.jianyueapi.mapper;

import com.spring.jianyueapi.entity.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "mobile",column = "mobile"),
            @Result(property = "password",column = "password"),
            @Result(property = "nickname",column = "nickname"),
            @Result(property = "avatar",column = "avatar"),
            @Result(property = "regtime",column = "regtime"),
            @Result(property = "token",column = "token")
    })
    @Select("SELECT * FROM t_user WHERE mobile = #{mobile} ")
    User getUserByMobile(String mobile);
    @Select("SELECT * FROM t_user WHERE id = #{id} ")
    User getUserById(Integer id);

    @Update("UPDATE t_user SET password= #{password},nickname= #{nickname},avatar= #{avatar},status= #{status},token= #{token} WHERE id= #{id} ")
    void update(User user);
}
