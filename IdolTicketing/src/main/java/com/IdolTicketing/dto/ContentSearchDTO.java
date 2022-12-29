package com.IdolTicketing.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContentSearchDTO {
    private String keyword;
    private category category;
    private String sortType;
    private String upDownType;
    private int limitCount;

    public ContentSearchDTO() {

    }
        public ContentSearchDTO(String keyword, category category, String sortType, String upDownType, int limitCount) {
        this.category = category;
        this.keyword = keyword;
        this.limitCount = limitCount;
        this.sortType = sortType;
        this.upDownType = upDownType;
    }
}
