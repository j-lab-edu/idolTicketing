package com.IdolTicketing.service;

import com.IdolTicketing.dto.ContentDTO;
import com.IdolTicketing.dto.ContentSearchDTO;

import java.util.List;

public interface ContentService {
    int createContent(ContentDTO contentDTO);

    int patchContent(ContentDTO contentDTO);

    ContentDTO deleteContent(ContentDTO contentDTO);

    ContentDTO getContent(String name);

    List<ContentDTO> selectContents(ContentSearchDTO contentSearchDTO);
}
