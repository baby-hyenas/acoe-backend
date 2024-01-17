package com.acoe.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminCafeSearchDto extends BaseDto{

    private String cafeNm;
    private String roadAddr;
    private Long discountAmt;

    private Boolean menuYn;

}
