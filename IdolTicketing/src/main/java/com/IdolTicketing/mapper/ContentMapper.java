package com.IdolTicketing.mapper;

import com.IdolTicketing.dto.ContentDTO;
import com.IdolTicketing.dto.ContentSearchDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContentMapper {
    int createContent(ContentDTO contentDTO);

    int patchContent(ContentDTO contentDTO);

    ContentDTO deleteContent(ContentDTO contentDTO);

    ContentDTO getContent(String name);

    ContentDTO getContentById(Integer contentId);

    List<ContentDTO> selectContents(ContentSearchDTO contentSearchDTO);
}
