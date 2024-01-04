package com.acoe.backend.service;

import com.acoe.backend.converter.FranchiseConverter;
import com.acoe.backend.dto.FranchiseDto;

import com.acoe.backend.converter.BaseConverter;
import com.acoe.backend.dto.MenuDto;
import com.acoe.backend.entity.Franchise;
import com.acoe.backend.repository.FranchiseRepository;
import com.acoe.backend.repository.MenuRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FranchiseService {
    private final FranchiseRepository   franchiseRepository;
    private final MenuRepository        menuRepository;
    private final FranchiseConverter    franchiseConverter;

    public List<FranchiseDto> searchDtoList(Boolean useYn) {
        List<Franchise> results;

        if(useYn == null) results = franchiseRepository.findAll();
        else results = franchiseRepository.findAllByUseYn(useYn);

        return results.stream().map(entity -> franchiseConverter.convertToGeneric(entity, FranchiseDto.class))
                .collect(Collectors.toList());
    }

    public FranchiseDto getFranchiseDto(Long franchiseId) {
        Objects.requireNonNull(franchiseId, "조회/수정/삭제시 Id는 필수입니다.");

        // 프랜차이즈 정보 조회
        FranchiseDto franchiseDto = franchiseConverter.convertToGeneric(franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new RuntimeException("해당 ID에 대한 프랜차이즈 정보가 없습니다.")), FranchiseDto.class);

        // 메뉴 조회
        List<MenuDto> menuDtos = menuRepository.findByFranchise_FranchiseId(franchiseId)
                .stream().map(menu -> franchiseConverter.convertToGeneric(menu, MenuDto.class))
                .collect(Collectors.toList());

        franchiseDto.setMenuList(menuDtos);

        return franchiseDto;
    }
}