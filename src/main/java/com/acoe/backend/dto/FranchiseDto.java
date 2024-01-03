package com.acoe.backend.dto;

import lombok.Data;
import java.util.List;

@Data
public class FranchiseDto extends BaseDto {
    private Long franchiseId;
    private String franchiseNm;
    private Long discountAmt;
    private byte[] logoImg;
    private Boolean useYn;
    private List<MenuDto> menuList;
}