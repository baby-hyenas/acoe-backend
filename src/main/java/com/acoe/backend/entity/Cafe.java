package com.acoe.backend.entity;

import com.acoe.backend.dto.CafeDto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;

import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_cafe")
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cafe extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long cafeId;

    @Column(name = "cafe_nm", length = 50)
    private String cafeNm;

    @Column(name = "area_cd")
    private Long areaCd;

    @Column(name = "trd_state_cd")
    private Long trdStateCd;

    @Column(name = "dtl_state_cd")
    private Long dtlStateCd;

    @Column(name = "tel_no", length = 30)
    private String telNo;

    @Column(name = "road_addr", length = 200)
    private String roadAddr;

    @Column(name = "x")
    @Digits(integer = 6, fraction = 3)
    private BigDecimal x;

    @Column(name = "y")
    @Digits(integer = 6, fraction = 3)
    private BigDecimal y;

    @Column(name = "discountAmt")
    private Long discountAmt;

    @Column(name = "appOrderYn")
    private Boolean appOrderYn;

    @Column(name = "kioskYn")
    private Boolean kioskYn;

    @Column(name = "useYn")
    private Boolean useYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;


    public static Cafe toEntity(CafeDto dto, Franchise franchise){
        return Cafe.builder()
                .cafeId(dto.getCafeId())
                .cafeNm(dto.getCafeNm())
                .areaCd(dto.getAreaCd())
                .trdStateCd(dto.getTrdStateCd())
                .dtlStateCd(dto.getDtlStateCd())
                .telNo(dto.getTelNo())
                .roadAddr(dto.getRoadAddr())
                .x(dto.getX())
                .y(dto.getY())
                .appOrderYn(dto.getAppOrderYn())
                .kioskYn(dto.getKioskYn())
                .useYn(dto.getUseYn())
                .discountAmt(dto.getDiscountAmt())
                .franchise(franchise)
                .build();
    }

    public void update(CafeDto dto, Franchise franchise){
        this.cafeNm = dto.getCafeNm();
        this.roadAddr = dto.getRoadAddr();
        this.discountAmt = dto.getDiscountAmt();
        this.appOrderYn = dto.getAppOrderYn();
        this.kioskYn = dto.getKioskYn();
        this.useYn = dto.getUseYn();
        this.franchise = franchise;
        this.x = dto.getX();
        this.y = dto.getY();
    }
}