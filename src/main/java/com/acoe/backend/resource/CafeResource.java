package com.acoe.backend.resource;

import com.acoe.backend.dto.BaseDto;
import com.acoe.backend.dto.MenuDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * A Resource for the  Cafe entity
 */
@Data
public class CafeResource extends BaseDto {
    private Long cafeId;
    private String cafeNm;
    private Long areaCd;
    private Long trdStateCd;
    private Long dtlStateCd;
    private String telNo;
    private String roadAddr;
    private String roadPostNo;
    private BigDecimal x;
    private BigDecimal y;
    private Long discountAmt;
    private String refNo;
    private Boolean appOrderYn;
    private Boolean kioskYn;
    private Boolean useYn;
    private FranchiseResource franchise;
    private List<MenuDto> menuList;
}