package com.IdolTicketing.mapper;

import com.IdolTicketing.dto.HelpDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HelpMapper {
    int createBoard(HelpDTO helpDTO);

    int updateBoard(HelpDTO helpDTO);

    HelpDTO getBoard(int id);

    int deleteBoard(HelpDTO helpDTO);

}
