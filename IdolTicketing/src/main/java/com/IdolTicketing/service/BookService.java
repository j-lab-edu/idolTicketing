package com.IdolTicketing.service;

import com.IdolTicketing.dto.BookDTO;

public interface BookService {
    int createBook(BookDTO bookDTO);

    int cancelBook(BookDTO bookDTO);

    BookDTO getBook(int id);

    int completeBook(int id);

    void scheduleExpiredBook();
}