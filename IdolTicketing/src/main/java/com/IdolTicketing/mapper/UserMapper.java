package com.IdolTicketing.mapper;

import com.IdolTicketing.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int registUser(UserDTO userDTO);

    UserDTO loginUser(UserDTO userDTO);

    int updateUser(UserDTO userDTO);

    int deleteUser(UserDTO userDTO);

    UserDTO getUser(String userId);

}
