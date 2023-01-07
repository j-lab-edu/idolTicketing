package com.IdolTicketing.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserDTO {
    private String userId;
    private String name;
    private String password;
    private String email;
    private int phone;
    private String address;
    private boolean isAdmin;
    private int id;
    private Date createTime;
    private Date updateTime;

    public UserDTO() {

    }

    public UserDTO(String userId, String name, String password, String email, int phone, String address, boolean isAdmin,
                   int id, Date createTime, Date updateTime) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.isAdmin = isAdmin;
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
