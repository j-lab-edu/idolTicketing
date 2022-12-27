package com.IdolTicketing.mapper;

import com.IdolTicketing.dto.BookDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper {
    int createBook(BookDTO bookDTO);

    int cancelBook(BookDTO bookDTO);

    BookDTO getBook(int id);

    int completeBook(int id);

    void scheduleExpiredBook();
}
