package com.acoe.backend.dto;

import java.math.BigDecimal;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CafeDto extends BaseDto {
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
    private FranchiseDto franchiseDto;
    private Long franchiseId;
    private List<MenuDto> menuList;

}