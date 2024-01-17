package com.acoe.backend.service;

import com.acoe.backend.converter.CafeConverter;
import com.acoe.backend.dto.AdminCafeSearchDto;
import com.acoe.backend.dto.CafeDto;
import com.acoe.backend.entity.Cafe;
import com.acoe.backend.entity.Franchise;
import com.acoe.backend.enums.MasterType;
import com.acoe.backend.exception.BusinessInvalidValueException;
import com.acoe.backend.repository.CafeRepository;
import com.acoe.backend.repository.FranchiseRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdminCafeServiceImpl implements AdminCafeService {

    private final FranchiseRepository franchiseRepository;
    private final CafeService cafeService;
    private final CafeRepository cafeRepository;
    private final AdminFranchiseService adminFranchiseService;
    private final AdminMenuService adminMenuService;
    private final CafeConverter cafeConverter;

    @Override
    public Page<CafeDto> searchAdminCafeDtoPage(AdminCafeSearchDto searchDto, Pageable pageable) {
        return cafeRepository.searchListByDynamicCond(searchDto, pageable).map(cafeConverter::convertEntityToDto);
    }

    @Override
    public CafeDto getCafeDto(Long cafeId) {
        return cafeService.getCafeDto(cafeId);
    }

    @Override
    @Transactional
    public Long updateCafe(CafeDto masterDto) {
        Objects.requireNonNull(masterDto.getCafeId(), "조회/수정/삭제시 Id는 필수입니다.");
        // 카페 수정
        Cafe cafe = cafeRepository.findById(masterDto.getCafeId())
                .orElseThrow(() -> new BusinessInvalidValueException("해당 ID에 대한 카페 정보가 없습니다."));

        // 프랜차이즈 카페는 메뉴정보 수정 불가
        Franchise franchise = null;
        if(masterDto.getFranchiseId() != null){
            franchise = adminFranchiseService.getFranchise(masterDto.getFranchiseId());
        } else {
            // 메뉴 수정/등록
            if(masterDto.getMenuList() != null) adminMenuService.saveMenus(masterDto, cafe);
        }

        // 카페 업데이트
        cafe.update(masterDto, franchise);
        cafeRepository.save(cafe);

        return cafe.getCafeId();
    }

    @Override
    @Transactional
    public Long createCafe(CafeDto dto) {
        Franchise franchise = null;
        if(dto.getFranchiseId() != null){
            franchise = franchiseRepository.findById(dto.getFranchiseId())
                    .orElseThrow(() -> new BusinessInvalidValueException("유효하지 않은 franchiseId"));
        }
        Cafe cafe = cafeRepository.save(Cafe.toEntity(dto, franchise));

        if(dto.getMenuList() != null && dto.getFranchiseId() == null) {
            adminMenuService.createMenus(dto.getMenuList(), cafe);
        }

        return cafe.getCafeId();
    }

    @Override
    @Transactional
    public void deleteCafe(Long cafeId) {
        Objects.requireNonNull(cafeId, "조회/수정/삭제시 Id는 필수입니다.");

        // 메뉴삭제
        adminMenuService.deleteMenus(MasterType.CAFE, cafeId);

        // 카페삭제
        cafeRepository.deleteById(cafeId);
    }
}
