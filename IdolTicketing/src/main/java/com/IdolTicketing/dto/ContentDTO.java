package com.IdolTicketing.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ContentDTO {

    private int id;
    private String name;
    private long price;
    private String description;
    private Date date;
    private String location;
    private String seat;
    private category category;
    private String userId;
    private Date createTime;
    private Date updateTime;
    private Date deadLine;
    private String keyword;
    private SortType sortType;
    private UpDownType upDownType;
    private int limitCount;

    public ContentDTO() {

    }

    public ContentDTO(int id, String name, long price, String description, Date date, String location,
                      String seat, category category, String userId, Date createTime, Date updateTime, Date deadLine, String keyword,
                      SortType sortType, UpDownType upDownType, int limitCount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.date = date;
        this.location = location;
        this.seat = seat;
        this.category = category;
        this.userId = userId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deadLine = deadLine;
        this.keyword = keyword;
        this.sortType = sortType;
        this.upDownType = upDownType;
        this.limitCount = limitCount;
    }
}

