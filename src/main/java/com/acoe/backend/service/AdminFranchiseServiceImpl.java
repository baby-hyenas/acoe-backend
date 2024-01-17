package com.acoe.backend.service;

import com.acoe.backend.dto.FranchiseDto;
import com.acoe.backend.entity.Franchise;

import java.util.List;
import java.util.Objects;

import com.acoe.backend.enums.MasterType;
import com.acoe.backend.exception.BusinessInvalidValueException;
import com.acoe.backend.repository.FranchiseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdminFranchiseServiceImpl implements AdminFranchiseService{

    private final FranchiseService franchiseService;
    private final AdminMenuService adminMenuService;
    private final FranchiseRepository franchiseRepository;

    @Override
    public List<FranchiseDto> getAllFranchiseDtoList() {
        return franchiseService.searchDtoList(null);
    }

    @Override
    public FranchiseDto getFranchiseDto(Long franchiseId) {
        return franchiseService.getFranchiseDto(franchiseId);
    }

    @Override
    public Franchise getFranchise(Long franchiseId) {
        return franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new BusinessInvalidValueException("해당 ID에 대한 프랜차이즈 정보가 없습니다."));
    }

    @Override
    @Transactional
    public Long createFranchise(FranchiseDto dto) {
        Franchise franchise = franchiseRepository.save(Franchise.toEntity(dto));

        if(dto.getMenuList() != null){
            adminMenuService.createMenus(dto.getMenuList(), franchise);
        }

        return franchise.getFranchiseId();
    }

    @Override
    @Transactional
    public Long updateFranchise(FranchiseDto masterDto) {
        Objects.requireNonNull(masterDto.getFranchiseId(), "조회/수정/삭제시 Id는 필수입니다.");

        // 프랜차이즈 수정
        Franchise franchise = franchiseRepository.findById(masterDto.getFranchiseId())
                .orElseThrow(() -> new BusinessInvalidValueException("해당 ID에 대한 프랜차이즈 정보가 없습니다."));
        franchise.update(masterDto);
        franchiseRepository.save(franchise);

        // 메뉴 등록/수정
        if(masterDto.getMenuList() != null) adminMenuService.saveMenus(masterDto, franchise);

        return franchise.getFranchiseId();
    }

    @Override
    @Transactional
    public void deleteFranchise(Long franchiseId) {
        Objects.requireNonNull(franchiseId, "조회/수정/삭제시 Id는 필수입니다.");

        // 메뉴삭제
        adminMenuService.deleteMenus(MasterType.FRANCHISE, franchiseId);

        // 프랜차이즈삭제
        franchiseRepository.deleteById(franchiseId);
    }
}
