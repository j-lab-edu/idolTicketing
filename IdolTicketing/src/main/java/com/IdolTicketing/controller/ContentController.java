package com.IdolTicketing.controller;

import com.IdolTicketing.aop.LoginCheck;
import com.IdolTicketing.dto.ContentDTO;
import com.IdolTicketing.dto.ContentSearchDTO;
import com.IdolTicketing.dto.category;
import com.IdolTicketing.exception.CNAdminException;
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
    @LoginCheck(types = LoginCheck.Role.ADMIN)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createContents(String userId,
                                         boolean isAdmin,
                                         @RequestBody ContentDTO contentDTO) {
        if (!isAdmin)
            throw new CNAdminException("");
        else
            contentService.createContent(contentDTO);
        return new ResponseEntity<>(contentDTO, HttpStatus.OK);

    }

    @PatchMapping("/{id}")
    @LoginCheck(types = LoginCheck.Role.ADMIN)
    public ResponseEntity<?> patchContent(String userId,
                                          boolean isAdmin,
                                          @RequestBody ContentDTO contentDTO) {

        if (!isAdmin)
            throw new CNAdminException("");
        else
            contentService.patchContent(contentDTO);
        return new ResponseEntity<>("수정되었습니다.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @LoginCheck(types = LoginCheck.Role.ADMIN)
    public ResponseEntity<?> deleteContents(String userId,
                                            boolean isAdmin,
                                            @RequestBody ContentDTO contentDTO) {
        if (!isAdmin)
            throw new CNAdminException("");
        else
            contentService.deleteContent(contentDTO);
        return new ResponseEntity<>("삭제되었습니다.", HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getContent(String userId,
                                        boolean isAdmin,
                                        @RequestParam String name) {
        if (contentService.getContent(name) == null)
            throw new RuntimeException("없는 정보입니다");
        else
            return new ResponseEntity<>(contentService.getContent(name), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ContentDTO>> selectContents(@RequestParam(value = "keyword", required = false) String keyword,
                                                           @RequestParam(value = "category", required = false) category category,
                                                           @RequestParam(value = "limitCount", required = false) int limitCount,
                                                           @RequestParam(value = "sortType", required = false) String sortType,
                                                           @RequestParam(value = "upDownType", required = false) String upDownType) {
        if (keyword == null)
            throw new RuntimeException("없는 정보입니다.");
        else
            return new ResponseEntity<>(contentService.selectContents(
                    ContentSearchDTO.builder()
                            .sortType(sortType)
                            .upDownType(upDownType)
                            .category(category)
                            .keyword(keyword)
                            .limitCount(limitCount)
                            .build()), HttpStatus.OK);
    }
}

