package com.acoe.backend.resource;

import com.acoe.backend.dto.BaseDto;
import com.acoe.backend.dto.MenuDto;
import lombok.Data;

import java.util.List;


/**
 * A Resource for the Franchise entity
 */
@Data
public class FranchiseResource extends BaseDto {
    private Long franchiseId;
    private String franchiseNm;
    private Long discountAmt;
    private String logoImg;
    private Boolean useYn;
    private List<MenuDto> menuList;
}