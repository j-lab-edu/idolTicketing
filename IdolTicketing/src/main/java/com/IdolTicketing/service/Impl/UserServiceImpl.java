package com.IdolTicketing.service.Impl;

import com.IdolTicketing.dto.UserDTO;
import com.IdolTicketing.mapper.UserMapper;
import com.IdolTicketing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    private MessageSource messageSource;

    @Override
    public int register(UserDTO userDTO) {
        userDTO.setAddress(messageSource.getMessage("users.address", null, LocaleContextHolder.getLocale()));
        return userMapper.registUser(userDTO);
    }

    @Override
    public UserDTO login(UserDTO userDTO) {
        userDTO.setAddress(messageSource.getMessage("users.address", null, LocaleContextHolder.getLocale()));
        return userMapper.loginUser(userDTO);
    }

    @Override
    public int update(UserDTO userDTO) {
        userDTO.setAddress(messageSource.getMessage("users.address", null, LocaleContextHolder.getLocale()));
        return userMapper.updateUser(userDTO);
    }

    @Override
    public UserDTO getUserInfo(String userId) {
        return userMapper.getUser(userId);
    }

    @Override
    public int delete(UserDTO userDTO) {
        userDTO.setAddress(messageSource.getMessage("users.address", null, LocaleContextHolder.getLocale()));
        return userMapper.deleteUser(userDTO);
    }
}
