package com.IdolTicketing.service.Impl;

import com.IdolTicketing.dto.BookDTO;
import com.IdolTicketing.dto.ContentDTO;
import com.IdolTicketing.exception.CCategoryNotFoundException;
import com.IdolTicketing.exception.CNameNotFoundException;
import com.IdolTicketing.mapper.BookMapper;
import com.IdolTicketing.mapper.ContentMapper;
import com.IdolTicketing.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookMapper bookMapper;
   // private MessageSource messageSource;

    @Autowired
    ContentMapper contentMapper;

    @Override
    public int createBook(BookDTO bookDTO) {
        ContentDTO contentDTO = contentMapper.getContentById(bookDTO.getContentId());
        bookDTO.setExpireTime(contentDTO.getDeadLine());
        //bookDTO.setName(messageSource.getMessage("books.name", null, LocaleContextHolder.getLocale()));
        if(bookDTO.getCategory()==null){
            throw new CCategoryNotFoundException ("805 category 정보가 부족합니다.");
        }else if(bookDTO.getName()==null){
            throw new CNameNotFoundException("806 name 정보가 부족합니다.");
        }
        return bookMapper.createBook(bookDTO);
    }

    @Override
    public int cancelBook(BookDTO bookDTO) {
        //bookDTO.setName(messageSource.getMessage("books.name", null, LocaleContextHolder.getLocale()));
        return bookMapper.cancelBook(bookDTO);
    }

    @Override
    public BookDTO getBook(int id) {
        return bookMapper.getBook(id);
    }

    @Override
    public int completeBook(int id) {
        return bookMapper.completeBook(id);
    }

    @Override
    public void scheduleExpiredBook() {
        bookMapper.scheduleExpiredBook();
    }
}
