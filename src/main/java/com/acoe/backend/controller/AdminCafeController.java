package com.acoe.backend.controller;

import com.acoe.backend.converter.CafeConverter;
import com.acoe.backend.dto.AdminCafeSearchDto;
import com.acoe.backend.dto.CafeDto;
import com.acoe.backend.resource.AdminCafeSearchResource;
import com.acoe.backend.resource.AdminCafeUpdateResource;
import com.acoe.backend.resource.CafeResource;
import com.acoe.backend.service.AdminCafeService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/api/admin/cafe")
public class AdminCafeController {

    private final AdminCafeService adminCafeService;
    private final CafeConverter cafeConverter;

    @GetMapping("/cafes")
    public Page<CafeResource> searchAdminCafeList(@Valid @io.swagger.v3.oas.annotations.parameters.RequestBody AdminCafeSearchResource searchResource){
        Page<CafeDto> cafeDtos = adminCafeService.searchAdminCafeDtoPage(cafeConverter.convertToGeneric(searchResource, AdminCafeSearchDto.class), searchResource.getPageInfo());

        // convert to resource
        return cafeDtos.map(cafeConverter::convertDtoToResource);
    }

    @GetMapping("/{cafeId}")
    public CafeResource getCafe(@PathVariable("cafeId") Long cafeId){
        return cafeConverter.convertDtoToResource(adminCafeService.getCafeDto(cafeId));
    }

    @PostMapping("/")
    public Long updateCafe(@PathVariable("cafeId") Long cafeId, AdminCafeUpdateResource updateResource)
    {
        return adminCafeService.updateCafe(cafeConverter.convertToGeneric(updateResource, CafeDto.class));
    }

    @DeleteMapping("/{cafeId}")
    public void deleteCafe(@PathVariable("cafeId") Long cafeId){
        adminCafeService.deleteCafe(cafeId);
    }



}
