package com.IdolTicketing.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminResponseDTO {
    private int code;
    private String massage;
}
