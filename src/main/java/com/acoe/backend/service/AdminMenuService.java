package com.acoe.backend.service;


import com.acoe.backend.dto.CafeDto;
import com.acoe.backend.dto.FranchiseDto;
import com.acoe.backend.dto.MenuDto;
import com.acoe.backend.entity.Cafe;
import com.acoe.backend.entity.Franchise;

import java.util.List;

public interface AdminMenuService {
    void saveMenus(CafeDto cafeDto, Cafe cafe);

    void saveMenus(FranchiseDto franchiseDto, Franchise franchise);

    void createMenus(List<MenuDto> menuDtoList, Cafe cafe);


    void createMenus(List<MenuDto> menuDtoList, Franchise franchise);

    void deleteMenus(Enum masterType, Long masterId);
}
