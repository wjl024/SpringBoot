package com.spring.jianyueapi.service.impl;

import com.spring.jianyueapi.entity.User;
import com.spring.jianyueapi.entity.dto.UserDTO;
import com.spring.jianyueapi.mapper.UserMapper;
import com.spring.jianyueapi.service.UserService;
import com.spring.jianyueapi.util.StatusConst;
import com.spring.jianyueapi.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User getUserByMobile(String mobile) {
        return userMapper.getUserByMobile(mobile);
    }

    @Override
    public int signIn(UserDTO userDTO) {
        User user=userMapper.getUserByMobile(userDTO.getMobile());
        //手机号存在
        if(user !=null){
            //密码正确
            if(userDTO.getPassword().equals(user.getPassword())){
                //账号正常
                if(user.getStatus()==1){
                    user.setToken(StringUtil.getUUIDString());
                    userMapper.update(user);
                    return StatusConst.SUCCESS;
                }else{
                    //账号异常
                    return StatusConst.USER_STATUS_ERROR;
                }
            }else{
                //密码错误
                return StatusConst.PASSWORD_ERROR;
            }
        }else{
            //手机号不存在
            return StatusConst.USER_MOBILE_NOT_FOUND;
        }
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void signUp(UserDTO userDTO) {
        User user1=new User();
        user1.setMobile(userDTO.getMobile());
        user1.setPassword(StringUtil.getBase64Encoder(userDTO.getPassword()));
        user1.setNickname("新用户");
        user1.setAvatar("http://ppeto2k90.bkt.clouddn.com/avatar/default.png");
        user1.setRegtime(new Date());
        user1.setStatus((short)1);
        userMapper.insert(user1);
    }
}
