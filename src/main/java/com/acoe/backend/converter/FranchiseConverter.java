package com.acoe.backend.converter;

import com.acoe.backend.dto.FranchiseDto;
import com.acoe.backend.resource.FranchiseResource;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class FranchiseConverter extends BaseConverter {

    public FranchiseResource convertToResource(FranchiseDto franchiseDto){
        FranchiseResource franchiseResource = convertToGeneric(franchiseDto, FranchiseResource.class);
        if(franchiseDto.getLogoImg() != null) franchiseResource.setLogoImg(Base64.getEncoder().encodeToString(franchiseDto.getLogoImg()));

        return franchiseResource;
    }

    public FranchiseDto convertToDto(FranchiseResource franchiseResource){
        FranchiseDto franchiseDto = convertToGeneric(franchiseResource, FranchiseDto.class);
        if(franchiseResource.getLogoImg() != null) franchiseDto.setLogoImg(Base64.getDecoder().decode(franchiseResource.getLogoImg()));

        return franchiseDto;
    }

}