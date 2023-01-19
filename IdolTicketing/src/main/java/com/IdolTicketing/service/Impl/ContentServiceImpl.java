package com.IdolTicketing.service.Impl;

import com.IdolTicketing.dto.ContentDTO;
import com.IdolTicketing.dto.ContentSearchDTO;
import com.IdolTicketing.mapper.ContentMapper;
import com.IdolTicketing.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    ContentMapper contentMapper;
    private MessageSource messageSource;


    @Override
    public int createContent(ContentDTO contentDTO) {
        contentDTO.setName(messageSource.getMessage("contents.name", null, LocaleContextHolder.getLocale()));
        contentDTO.setLocation(messageSource.getMessage("contents.location", null, LocaleContextHolder.getLocale()));
        contentDTO.setSeat(messageSource.getMessage("contents.seat", null, LocaleContextHolder.getLocale()));
        return contentMapper.createContent(contentDTO);
    }

    @Override
    public int patchContent(ContentDTO contentDTO) {
        contentDTO.setName(messageSource.getMessage("contents.name", null, LocaleContextHolder.getLocale()));
        contentDTO.setLocation(messageSource.getMessage("contents.location", null, LocaleContextHolder.getLocale()));
        contentDTO.setSeat(messageSource.getMessage("contents.seat", null, LocaleContextHolder.getLocale()));
        return contentMapper.patchContent(contentDTO);
    }

    @Override
    public ContentDTO deleteContent(ContentDTO contentDTO) {
        contentDTO.setName(messageSource.getMessage("contents.name", null, LocaleContextHolder.getLocale()));
        contentDTO.setLocation(messageSource.getMessage("contents.location", null, LocaleContextHolder.getLocale()));
        contentDTO.setSeat(messageSource.getMessage("contents.seat", null, LocaleContextHolder.getLocale()));
        return contentMapper.deleteContent(contentDTO);
    }

    @Override
    public ContentDTO getContent(String name) {
        return contentMapper.getContent(name);
    }

    @Override
    public List<ContentDTO> selectContents(ContentSearchDTO contentSearchDTO) {
        return contentMapper.selectContents(contentSearchDTO);
    }
}
