package com.IdolTicketing.service.Impl;

import com.IdolTicketing.dto.BookDTO;
import com.IdolTicketing.mapper.BookMapper;
import com.IdolTicketing.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookMapper bookMapper;

    @Override
    public int createBook(BookDTO bookDTO) {
        return bookMapper.createBook(bookDTO);
    }

    @Override
    public int cancelBook(BookDTO bookDTO) {
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
