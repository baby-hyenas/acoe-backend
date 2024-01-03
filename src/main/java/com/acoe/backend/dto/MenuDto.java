package com.acoe.backend.dto;

import lombok.Data;

@Data
public class MenuDto extends BaseDto {
    private Long menuId;
    private String menuNm;
    private Long price;
}