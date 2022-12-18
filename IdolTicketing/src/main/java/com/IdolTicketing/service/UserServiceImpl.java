package com.IdolTicketing.service;

import com.IdolTicketing.dto.UserDTO;
import com.IdolTicketing.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public int register(UserDTO userDTO) {
        return userMapper.registUser(userDTO);
    }
}
