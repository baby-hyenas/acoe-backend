package com.acoe.backend.controller;


import com.acoe.backend.converter.CafeConverter;
import com.acoe.backend.converter.FranchiseConverter;
import com.acoe.backend.dto.CafeDto;
import com.acoe.backend.resource.CafeResource;
import com.acoe.backend.resource.FranchiseResource;
import com.acoe.backend.service.AdminCafeService;
import com.acoe.backend.service.AdminFranchiseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/api/admin/franchise")
public class AdminFranchiseController {
    private final AdminFranchiseService adminFranchiseService;
    private final FranchiseConverter franchiseConverter;
    /**
     * 프랜차이즈 목록 조회(ADMIN)
     */
    @GetMapping("/franchises")
    public List<FranchiseResource> searchAdminFranchiseList(){
        return adminFranchiseService.getAllFranchiseDtoList().stream()
                .map(franchiseConverter::convertToResource)
                .collect(Collectors.toList());
    }

    /**
     * 관리자 프랜차이즈 상세 조회(ADMIN)
     */
    @GetMapping("/{franchiseId}")
    public FranchiseResource getFranchise(@PathVariable("franchiseId") Long franchiseId){
        return franchiseConverter.convertToResource(adminFranchiseService.getFranchiseDto(franchiseId));
    }

    @PostMapping("/")
    public Long createFranchise(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody FranchiseResource resource){
        return adminFranchiseService.createFranchise(franchiseConverter.convertToDto(resource));
    }


    @PutMapping("/{franchiseId}")
    public Long updateFranchise(@PathVariable("franchiseId") Long franchiseId,
                                @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody FranchiseResource resource)
    {
        return adminFranchiseService.updateFranchise(franchiseConverter.convertToDto(resource));
    }

    @DeleteMapping("/{franchiseId}")
    public void deleteFranchise(@PathVariable("franchiseId") Long franchiseId){
        adminFranchiseService.deleteFranchise(franchiseId);
    }
}
