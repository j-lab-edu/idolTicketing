package com.IdolTicketing.mapper;

import com.IdolTicketing.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int registUser(UserDTO userDTO);
}
