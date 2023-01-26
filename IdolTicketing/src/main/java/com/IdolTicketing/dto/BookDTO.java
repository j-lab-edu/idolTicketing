package com.IdolTicketing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class BookDTO {
    private String userId;
    private String name;
    private category category;
    private Date createTime;
    private Date updateTime;
    private int id;
    private BookState bookState;
    private Date expireTime;
    private Integer contentId;

    public BookDTO(String userId, String name, Date createTime, Date updateTime, int id) {
        this.userId = userId;
        this.name = name;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.id = id;
    }

    public BookDTO() {

    }
}
