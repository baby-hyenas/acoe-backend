package com.acoe.backend.controller;

import com.acoe.backend.converter.FranchiseConverter;
import com.acoe.backend.dto.FranchiseDto;
import com.acoe.backend.resource.FranchiseResource;
import com.acoe.backend.service.FranchiseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/api/main/franchise")
public class FranchiseController {
    private final FranchiseService franchiseService;
    private final FranchiseConverter franchiseConverter;
    
    @GetMapping("/franchises")
    public List<FranchiseResource> searchList(){
        return franchiseService.searchDtoList(true).stream().map(franchiseConverter::convertToResource)
                .collect(Collectors.toList());
    }


}