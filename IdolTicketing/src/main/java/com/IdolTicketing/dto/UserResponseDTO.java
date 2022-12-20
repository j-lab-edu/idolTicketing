package com.IdolTicketing.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDTO {
    private int code;
    private String massage;
}
