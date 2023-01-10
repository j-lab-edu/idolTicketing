package com.IdolTicketing.service.Impl;

import com.IdolTicketing.dto.HelpDTO;
import com.IdolTicketing.mapper.HelpMapper;
import com.IdolTicketing.service.HelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelpServiceImpl implements HelpService {
    @Autowired
    HelpMapper helpMapper;

    @Override
    public int helpBoard(HelpDTO helpDTO) {
        return helpMapper.createBoard(helpDTO);
    }

    @Override
    public int updateBoard(HelpDTO helpDTO) {
        return helpMapper.updateBoard(helpDTO);
    }

    @Override
    public int deleteBoard(HelpDTO helpDTO) {
        return helpMapper.deleteBoard(helpDTO);
    }

    @Override
    public HelpDTO getBoard(int id) {
        return helpMapper.getBoard(id);
    }

}
