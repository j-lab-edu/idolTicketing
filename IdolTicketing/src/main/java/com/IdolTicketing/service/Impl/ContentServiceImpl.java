package com.IdolTicketing.service.Impl;

import com.IdolTicketing.dto.ContentDTO;
import com.IdolTicketing.dto.ContentSearchDTO;
import com.IdolTicketing.exception.CCategoryNotFoundException;
import com.IdolTicketing.exception.CDescriptionNotFound;
import com.IdolTicketing.exception.CNameNotFoundException;
import com.IdolTicketing.exception.CSeatNotFound;
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
        if (contentDTO.getCategory() == null)
            throw new CCategoryNotFoundException("");
        else if (contentDTO.getName() == null)
            throw new CNameNotFoundException("");
        else if (contentDTO.getDescription() == null)
            throw new CDescriptionNotFound("");
        else if (contentDTO.getSeat() == null)
            throw new CSeatNotFound("");

        contentDTO.setSeat(messageSource.getMessage("content.seat", null, LocaleContextHolder.getLocale()));
        contentDTO.setName(messageSource.getMessage("content.name", null, LocaleContextHolder.getLocale()));
        contentDTO.setLocation(messageSource.getMessage("content.location", null, LocaleContextHolder.getLocale()));

        return contentMapper.createContent(contentDTO);
    }

    @Override
    public int patchContent(ContentDTO contentDTO) {
        contentDTO.setSeat(messageSource.getMessage("content.seat", null, LocaleContextHolder.getLocale()));
        contentDTO.setName(messageSource.getMessage("content.name", null, LocaleContextHolder.getLocale()));
        contentDTO.setLocation(messageSource.getMessage("content.location", null, LocaleContextHolder.getLocale()));
        return contentMapper.patchContent(contentDTO);
    }

    @Override
    public ContentDTO deleteContent(ContentDTO contentDTO) {
        return contentMapper.deleteContent(contentDTO);
    }

    @Override
    public ContentDTO getContent(String name) {
        return contentMapper.getContent(name);
    }

    @Override
    public ContentDTO getContentById(Integer contentId) {
        return contentMapper.getContentById(contentId);
    }

    @Override
    public List<ContentDTO> selectContents(ContentSearchDTO contentSearchDTO) {
        return contentMapper.selectContents(contentSearchDTO);
    }
}
