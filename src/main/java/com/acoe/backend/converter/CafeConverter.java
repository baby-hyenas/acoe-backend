package com.acoe.backend.converter;

import com.acoe.backend.dto.CafeDto;
import com.acoe.backend.dto.FranchiseDto;
import com.acoe.backend.entity.Cafe;
import com.acoe.backend.resource.CafeResource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CafeConverter extends BaseConverter {

    private final FranchiseConverter franchiseConverter;

    public CafeResource convertDtoToResource(CafeDto cafeDto){
        if(cafeDto == null) return null;

        CafeResource cafeResource = convertToGeneric(cafeDto, CafeResource.class);
        if(cafeDto.getMenuList() != null){
            cafeResource.setMenuList(cafeDto.getMenuList());
        }
        if(cafeDto.getFranchiseDto() != null){
            cafeResource.setFranchise(franchiseConverter.convertToResource(cafeDto.getFranchiseDto()));
        }
        return cafeResource;
    }

    public CafeDto convertEntityToDto(Cafe cafe){
        CafeDto cafeDto = convertToGeneric(cafe, CafeDto.class);
        if(cafe.getFranchise() != null){
            cafeDto.setFranchiseDto(convertToGeneric(cafe.getFranchise(), FranchiseDto.class));
        }
        return cafeDto;
    }

}