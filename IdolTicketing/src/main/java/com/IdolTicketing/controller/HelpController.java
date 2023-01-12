package com.IdolTicketing.controller;

import com.IdolTicketing.aop.LoginCheck;
import com.IdolTicketing.dto.HelpDTO;
import com.IdolTicketing.service.HelpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/helps")
public class HelpController {

    private final HelpService helpService;

    public HelpController(HelpService helpService) {
        this.helpService = helpService;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @LoginCheck(type = LoginCheck.Role.USER)
    public ResponseEntity helpBoard(String userId,
                                    boolean isAdmin,
                                    @RequestBody HelpDTO helpDTO) {
        if (userId.equals(helpDTO.getUserId())) {
            helpService.helpBoard(helpDTO);
        } else {
            return new ResponseEntity("잘못된 접근입니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(helpDTO, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @LoginCheck(type = LoginCheck.Role.USER)
    public ResponseEntity updateBoard(String userId,
                                      boolean isAdmin,
                                      @RequestBody HelpDTO helpDTO) {
        if (userId.equals(helpDTO.getUserId())) {
            helpService.updateBoard(helpDTO);
        } else {
            return new ResponseEntity("잘못된 접근입니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(helpDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @LoginCheck(type = LoginCheck.Role.USER)
    public ResponseEntity deleteBoard(String userId,
                                      boolean isAdmin,
                                      @RequestBody HelpDTO helpDTO) {
        if (userId.equals(helpDTO.getUserId())) {
            helpService.deleteBoard(helpDTO);
        } else {
            return new ResponseEntity("잘못된 접근입니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("삭제되었습니다.", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @LoginCheck(type = LoginCheck.Role.USER)
    public ResponseEntity getBoard(String userId,
                                   boolean isAdmin,
                                   @PathVariable int id) {

        return new ResponseEntity<>((helpService.getBoard(id)), HttpStatus.OK);
    }

}
