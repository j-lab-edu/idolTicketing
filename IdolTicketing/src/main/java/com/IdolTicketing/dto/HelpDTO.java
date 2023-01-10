package com.IdolTicketing.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class HelpDTO {
    private int id;
    private String title;
    private String description;
    private String userId;
    private Boolean isAdmin;

    public HelpDTO() {
    }

    public HelpDTO(int id, String title, String description, String userId, Boolean isAdmin) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.isAdmin = isAdmin;
    }
}
