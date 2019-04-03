package com.spring.jianyueapi.service.impl;

import com.spring.jianyueapi.entity.User;
import com.spring.jianyueapi.entity.dto.UserDTO;
import com.spring.jianyueapi.service.UserService;
import com.spring.jianyueapi.util.StatusConst;
import com.spring.jianyueapi.util.StringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.swing.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Resource
    private UserService userService;
    @Test
    public void getUserByMobile() {
        User user=userService.getUserByMobile("18852009609");
        System.out.println(user);
    }
    @Test
    public void getUSerById(){
        User user=userService.getUserById(1);
        System.out.println(user);
    }
    @Test
    public void signIn() {
        UserDTO loginUser=new UserDTO();
        loginUser.setMobile("18852009609");
        String base64pass= StringUtil.getBase64Encoder("1234");
        loginUser.setPassword(base64pass);
        int status=userService.signIn(loginUser);
        assertEquals(StatusConst.SUCCESS,status);
    }
}