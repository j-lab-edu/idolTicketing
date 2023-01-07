package com.IdolTicketing.controller;

import com.IdolTicketing.aop.LoginCheck;
import com.IdolTicketing.dto.AdminResponseDTO;
import com.IdolTicketing.dto.ContentDTO;
import com.IdolTicketing.dto.ContentSearchDTO;
import com.IdolTicketing.dto.category;
import com.IdolTicketing.service.ContentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contents")
public class ContentController {

    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @PostMapping("/")
    @LoginCheck(types = {LoginCheck.Role.USER, LoginCheck.Role.ADMIN})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createContents(String userId,
                                      boolean isAdmin,
                                      @RequestBody ContentDTO contentDTO) {
        if (!isAdmin) {
            return new ResponseEntity<>(AdminResponseDTO.builder()
                    .code(403)
                    .massage("관리자가 아닙니다.")
                    .build(), HttpStatus.BAD_REQUEST);
        } else {
            contentService.createContent(contentDTO);
            return new ResponseEntity<>(contentDTO, HttpStatus.OK);
        }
    }

    @PatchMapping("/{id}")
    @LoginCheck(types = {LoginCheck.Role.USER, LoginCheck.Role.ADMIN})
    public ResponseEntity<?> patchContent(String userId,
                                          boolean isAdmin,
                                          @RequestBody ContentDTO contentDTO) {

        if (!isAdmin) {
            contentService.patchContent(contentDTO);
        } else {
            return new ResponseEntity<>(AdminResponseDTO.builder()
                    .code(403)
                    .massage("관리자가 아닙니다.")
                    .build(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("수정되었습니다.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @LoginCheck(types = {LoginCheck.Role.USER, LoginCheck.Role.ADMIN})
    public ResponseEntity<?> deleteContents(String userId,
                                         boolean isAdmin,
                                         @RequestBody ContentDTO contentDTO) {
        if (!isAdmin) {
            contentService.deleteContent(contentDTO);
        } else {
            return new ResponseEntity<>(AdminResponseDTO.builder()
                    .code(403)
                    .massage("관리자가 아닙니다.")
                    .build(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("삭제되었습니다.", HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getContent(String userId,
                                        boolean isAdmin,
                                        @RequestParam String name) {
        if (contentService.getContent(name) == null) {
        } else {
            return new ResponseEntity<>("없는 정보입니다", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(contentService.getContent(name), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ContentDTO>> selectContents(@RequestParam(value = "keyword", required = false) String keyword,
                                                        @RequestParam(value = "category", required = false) category category,
                                                        @RequestParam(value = "limitCount", required = false) int limitCount,
                                                        @RequestParam(value = "sortType", required = false) String sortType,
                                                        @RequestParam(value = "upDownType", required = false) String upDownType) {
        if (keyword == null) {
            return new ResponseEntity<List<ContentDTO>>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<List<ContentDTO>>(contentService.selectContents(
                    ContentSearchDTO.builder()
                            .sortType(sortType)
                            .upDownType(upDownType)
                            .category(category)
                            .keyword(keyword)
                            .limitCount(limitCount)
                            .build()), HttpStatus.OK);
        }
    }
}

