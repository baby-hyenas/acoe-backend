package com.acoe.backend.controller;

import com.acoe.backend.dto.FranchiseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/api/main/franchise")
public class FranchiseController {
//    private final FranchiseService franchiseService;
//    private final FranchiseConverter franchiseConverter;
    
    @GetMapping("/franchises")
    public List<FranchiseDto> searchList(){
        return new ArrayList<>();
//        return franchiseService.searchDtoList(true).stream().map(franchiseConverter::convertToResource)
//                .collect(Collectors.toList());
    }


}