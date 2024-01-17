package com.acoe.backend.service;

import com.acoe.backend.dto.AdminCafeSearchDto;
import com.acoe.backend.dto.CafeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface AdminCafeService {

    Page<CafeDto> searchAdminCafeDtoPage(AdminCafeSearchDto searchDto, Pageable pageable);

    CafeDto getCafeDto(Long cafeId);

    Long updateCafe(CafeDto dto);

    Long createCafe(CafeDto dto);

    void deleteCafe(Long cafeId);

}
