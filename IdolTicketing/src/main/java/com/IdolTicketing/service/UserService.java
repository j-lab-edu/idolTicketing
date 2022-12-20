package com.IdolTicketing.service;

import com.IdolTicketing.dto.UserDTO;

public interface UserService {
    int register(UserDTO userDTO);
    UserDTO login(UserDTO userDTO);
    int update(UserDTO userDTO);
    UserDTO getUserInfo(String userId);
    int delete(UserDTO userDTO);
}
