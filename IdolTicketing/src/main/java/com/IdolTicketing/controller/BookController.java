package com.IdolTicketing.controller;

import com.IdolTicketing.aop.LoginCheck;
import com.IdolTicketing.dto.BookDTO;
import com.IdolTicketing.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/")
    @LoginCheck(types = {LoginCheck.Role.ADMIN, LoginCheck.Role.USER})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createBook(String userId,
                                     boolean isAdmin,
                                     @RequestBody BookDTO bookDTO) {

        if (userId.equals(bookDTO.getUserId())) {
            bookService.createBook(bookDTO);
        } else {
            return new ResponseEntity<>("잘못된 접근입니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("예매되었습니다.", HttpStatus.OK);
    }

    @PatchMapping("/cancel")
    @LoginCheck(types = {LoginCheck.Role.ADMIN, LoginCheck.Role.USER})
    public ResponseEntity cancelBook(String userId,
                                     boolean isAdmin,
                                     @RequestBody BookDTO bookDTO) {
        if (userId.equals(bookDTO.getUserId())) {
            bookService.cancelBook(bookDTO);
        } else {
            return new ResponseEntity<>("잘못된 접근입니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("취소되었습니다.", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @LoginCheck(types = {LoginCheck.Role.ADMIN, LoginCheck.Role.USER})
    public ResponseEntity getBook(String userId,
                                  boolean isAdmin,
                                  @PathVariable int id) {

        return new ResponseEntity<>((bookService.getBook(id)), HttpStatus.OK);
    }

    @PatchMapping("/complete/{id}")
    @LoginCheck(types = {LoginCheck.Role.ADMIN, LoginCheck.Role.USER})
    public ResponseEntity completeBook(String userId,
                                       boolean isAdmin,
                                       @PathVariable int id) {

        if (bookService.completeBook(id) != 0) {
            return new ResponseEntity<>("사용되었습니다.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("잘못된 접근입니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @Scheduled(cron = "0 1 0 * * *")
    public void scheduleExpiredBook() {
        bookService.scheduleExpiredBook();
    }

}
