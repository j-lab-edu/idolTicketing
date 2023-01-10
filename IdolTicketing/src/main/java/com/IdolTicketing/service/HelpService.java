package com.IdolTicketing.service;

import com.IdolTicketing.dto.HelpDTO;

public interface HelpService {
    int helpBoard(HelpDTO helpDTO);

    int updateBoard(HelpDTO helpDTO);

    int deleteBoard(HelpDTO helpDTO);

    HelpDTO getBoard(int id);

}
