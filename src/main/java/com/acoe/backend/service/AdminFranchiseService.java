package com.acoe.backend.service;

import com.acoe.backend.dto.FranchiseDto;
import com.acoe.backend.entity.Franchise;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface AdminFranchiseService {

    List<FranchiseDto> getAllFranchiseDtoList();
    FranchiseDto getFranchiseDto(Long franchiseId);
    Franchise getFranchise(Long franchiseId);
    Long createFranchise(FranchiseDto dto);
    Long updateFranchise(FranchiseDto dto);
    void deleteFranchise(Long franchiseId);

}
