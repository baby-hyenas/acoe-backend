package com.acoe.backend.service;

import com.acoe.backend.converter.CafeConverter;
import com.acoe.backend.dto.CafeDto;
import com.acoe.backend.dto.MenuDto;
import com.acoe.backend.entity.Cafe;
import com.acoe.backend.repository.CafeRepository;
import com.acoe.backend.repository.MenuRepository;
import com.acoe.backend.service.CafeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CafeService {
    private final MenuRepository menuRepository;
    private final CafeRepository cafeRepository;
    private final CafeConverter converter;

    public List<CafeDto> searchDtoList(Long areaCd, Long trdStateCd) {
        List<Cafe> result;

        if(trdStateCd == null) {
            result = cafeRepository.findByAreaCd(areaCd);
        } else {
            result = cafeRepository.findByAreaCdAndTrdStateCd(areaCd, trdStateCd);
        }

        return result.stream().map(e -> converter.convertToGeneric(e, CafeDto.class)).collect(Collectors.toList());
    }

    public List<CafeDto> searchDtoListByKeyword(String keyword) {
        return cafeRepository.findByCafeNmContains(keyword).stream()
                .filter(e -> e.getTrdStateCd() == 1L)
                .map(e -> converter.convertToGeneric(e, CafeDto.class)).collect(Collectors.toList());
    }

    public CafeDto getCafeDto(Long cafeId) {
        Objects.requireNonNull(cafeId, "조회/수정/삭제시 ID는 필수입니다.");

        // 카페 조회
        CafeDto cafeDto = converter.convertEntityToDto(
                cafeRepository.findById(cafeId)
                        .orElseThrow(() -> new RuntimeException("유효하지 않은 cafeId : " + cafeId)));

        // 메뉴조회
        List<MenuDto> menuDtos;
        if(cafeDto.getFranchiseDto() == null){
            menuDtos = menuRepository.findByCafe_CafeId(cafeId)
                    .stream().map(menu -> converter.convertToGeneric(menu, MenuDto.class))
                    .collect(Collectors.toList());
        } else {
            menuDtos = menuRepository.findByFranchise_FranchiseId(cafeDto.getFranchiseDto().getFranchiseId())
                    .stream().map(menu -> converter.convertToGeneric(menu, MenuDto.class))
                    .collect(Collectors.toList());
            cafeDto.getFranchiseDto().setMenuList(menuDtos);
        }

        cafeDto.setMenuList(menuDtos);
        return cafeDto;
    }
}