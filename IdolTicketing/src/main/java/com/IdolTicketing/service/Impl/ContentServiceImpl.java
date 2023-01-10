package com.IdolTicketing.service.Impl;

import com.IdolTicketing.dto.ContentDTO;
import com.IdolTicketing.dto.ContentSearchDTO;
import com.IdolTicketing.mapper.ContentMapper;
import com.IdolTicketing.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    ContentMapper contentMapper;


    @Override
    public int createContent(ContentDTO contentDTO) {
        return contentMapper.createContent(contentDTO);
    }

    @Override
    public int patchContent(ContentDTO contentDTO) {
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
    public List<ContentDTO> selectContents(ContentSearchDTO contentSearchDTO) {
        return contentMapper.selectContents(contentSearchDTO);
    }
}
