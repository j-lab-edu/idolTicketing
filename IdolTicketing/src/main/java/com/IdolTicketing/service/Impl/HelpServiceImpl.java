package com.IdolTicketing.service.Impl;

import com.IdolTicketing.dto.HelpDTO;
import com.IdolTicketing.exception.CDescriptionNotFound;
import com.IdolTicketing.exception.CTitleNotFound;
import com.IdolTicketing.mapper.HelpMapper;
import com.IdolTicketing.service.HelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class HelpServiceImpl implements HelpService {
    @Autowired
    HelpMapper helpMapper;
    MessageSource messageSource;

    @Override
    public int helpBoard(HelpDTO helpDTO) {
        if(helpDTO.getDescription()==null)
            throw new CDescriptionNotFound("");
        else if(helpDTO.getTitle()==null)
            throw new CTitleNotFound("");

        helpDTO.setTitle(messageSource.getMessage("helptitle", null, LocaleContextHolder.getLocale()));
        return helpMapper.createBoard(helpDTO);
    }

    @Override
    public int updateBoard(HelpDTO helpDTO) {
        helpDTO.setTitle(messageSource.getMessage("helps.title", null, LocaleContextHolder.getLocale()));
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
