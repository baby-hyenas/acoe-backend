package com.acoe.backend.resource;

import com.acoe.backend.dto.MenuDto;

import java.math.BigDecimal;
import java.util.List;

public class AdminCafeUpdateResource {
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

    private Boolean appOrderYn;

    private Boolean kioskYn;

    private Boolean useYn;

    private Long franchiseId;

    private List<MenuDto> menuList;

}
