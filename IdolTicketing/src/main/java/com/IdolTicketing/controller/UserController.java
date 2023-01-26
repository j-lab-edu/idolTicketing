package com.IdolTicketing.controller;

import com.IdolTicketing.SessionUtil;
import com.IdolTicketing.aop.LoginCheck;
import com.IdolTicketing.dto.UserDTO;
import com.IdolTicketing.dto.UserResponseDTO;
import com.IdolTicketing.exception.CUserNotFoundException;
import com.IdolTicketing.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * REST는 REpresentative State Transfer의 약자로 분산 시스템을 위한 아키텍처다.
 * 네트워크를 경유해서 외부 서버에 접속하거나 필요한 정보를 불러오기 위한 구조이다.
 * REST 개념을 바탕으로 설계된 시스템을 'RESTFul'이라고 표현한다.
 * REST의 경우 클라이언트가 특정 URL에 접속하면 웹페이지를 그리는 것이 아니라 특정 정보 또는 특정 처리 결과를 텍스트로 반환한다.
 * 이런 RESTFul한 웹 서비스를 구축하기 위해 사용하는 것이 RestController이다.
 * RESTController는 HTML 코드를 전송하는 페이지에 비해 구조가 간단하다.(단순한 텍스트로 정보를 전송하기 떄문에)
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        userService.register(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO,
                                   HttpSession session) {

        UserDTO userInfo = userService.login(userDTO);

        if (userInfo == null) // 1. DB MYSQL 데이터가 없는 경우.
            throw new CUserNotFoundException("");

        if (!userInfo.isAdmin()) {
            SessionUtil.setLoginUserId(session, userInfo.getUserId());
            return new ResponseEntity<>(UserResponseDTO.builder()
                    .code(601)
                    .massage("USER").build(), HttpStatus.OK);
        } else
            SessionUtil.setLoginAdminId(session, userInfo.getUserId());
        return new ResponseEntity<>(UserResponseDTO.builder()
                .code(602)
                .massage("ADMIN").build(), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @LoginCheck(types = {LoginCheck.Role.ADMIN, LoginCheck.Role.USER})
    public ResponseEntity<?> update(String userId,
                                    boolean isAdmin,
                                    @RequestBody UserDTO userDTO) {
        if (userId.equals(userDTO.getUserId()))
            userService.update(userDTO);
        else
            throw new RuntimeException(String.valueOf(UserResponseDTO.builder()
                    .code(900)
                    .massage("잘못된 접근입니다.")
                    .build()));

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PatchMapping("/logout")
    @LoginCheck(types = {LoginCheck.Role.ADMIN, LoginCheck.Role.USER})
    public ResponseEntity<?> logout(String userId,
                                    boolean isAdmin,
                                    HttpSession session,
                                    @RequestBody UserDTO userDTO) {
        if (userId.equals(userDTO.getUserId()))
            SessionUtil.clear(session);
        else
            throw new RuntimeException(String.valueOf(UserResponseDTO.builder()
                    .code(900)
                    .massage("잘못된 접근입니다.")
                    .build()));

        return new ResponseEntity<>("로그아웃 되었습니다.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @LoginCheck(types = {LoginCheck.Role.ADMIN, LoginCheck.Role.USER})
    public ResponseEntity<?> delete(String userId,
                                    boolean isAdmin,
                                    @RequestBody UserDTO userDTO) {
        if (userId.equals(userDTO.getUserId()))
            userService.delete(userDTO);
        else
            throw new RuntimeException(String.valueOf(UserResponseDTO.builder()
                    .code(900)
                    .massage("잘못된 접근입니다.")
                    .build()));

        return new ResponseEntity<>("삭제되었습니다.", HttpStatus.OK);
    }

    @GetMapping("/myInfo")
    public ResponseEntity<UserDTO> userInfo(HttpSession session) {
        String userId = SessionUtil.getLoginUserId(session);
        UserDTO userInfo = userService.getUserInfo(userId);
        return new ResponseEntity<>((userInfo), HttpStatus.OK);
    }

    @GetMapping("/sessionExpire")
    public void sessionExpire(HttpSession session) {
        SessionUtil.clear(session);
    }
}


