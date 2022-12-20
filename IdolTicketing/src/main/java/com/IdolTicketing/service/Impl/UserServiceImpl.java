package com.IdolTicketing.service.Impl;

import com.IdolTicketing.dto.UserDTO;
import com.IdolTicketing.mapper.UserMapper;
import com.IdolTicketing.service.UserService;
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

    @Override
    public UserDTO login(UserDTO userDTO) {
        return userMapper.loginUser(userDTO);
    }

    @Override
    public int update(UserDTO userDTO) {
        return userMapper.updateUser(userDTO);
    }

    @Override
    public UserDTO getUserInfo(String userId) {
        return userMapper.getUser(userId);
    }

    @Override
    public int delete(UserDTO userDTO) {
        return userMapper.deleteUser(userDTO);
    }
}
