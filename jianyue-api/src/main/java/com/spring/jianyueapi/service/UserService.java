package com.spring.jianyueapi.service;

import com.spring.jianyueapi.entity.User;
import com.spring.jianyueapi.entity.dto.UserDTO;

public interface UserService {
    User getUserByMobile(String mobile);

    int signIn(UserDTO userDTO);
    User getUserById(Integer id);
    void update(User user);
}
