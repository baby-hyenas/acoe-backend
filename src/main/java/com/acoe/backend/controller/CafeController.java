package com.acoe.backend.controller;

import com.acoe.backend.dto.CafeDto;
import com.acoe.backend.service.CafeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/main/cafe")
public class CafeController {

    private final CafeService cafeService;
    //private final CafeConverter cafeConverter;


    /**
     * 메인 영업중 카페 목록 조회 API(마커용)
     */
    @GetMapping("/cafes/{areaCd}")
    public List<CafeDto> searchList(@PathVariable("areaCd") Long areaCd){
        List<CafeDto> cafeDtos = cafeService.searchDtoList(areaCd, 1L);

        return cafeDtos;
        // convert to resource
//        return cafeDtos.stream().map(cafeConverter::convertDtoToResource)
//                .collect(Collectors.toList());
    }

//    /**
//     * 메인 카페 키워드 목록 조회 API(목록용)
//     */
//    @GetMapping("/cafe-keyword/{keyword}")
//    public List<CafeResource> searchListByKeyword(@PathVariable("keyword") String keyword){
//        List<CafeDto> cafeDtos = cafeService.searchDtoListByKeyword(keyword);
//
//        // convert to resource
//        return cafeDtos.stream().map(cafeConverter::convertDtoToResource)
//                .collect(Collectors.toList());
//    }
//
//    /**
//     * 메인 카페 상세 조회 API
//     */
//    @GetMapping("/{cafeId}")
//    @Parameter(name = "cafeId", description = "카페ID", in = ParameterIn.PATH)
//    public CafeResource getCafe(@PathVariable("cafeId") Long cafeId){
//        return cafeConverter.convertDtoToResource(cafeService.getCafeDto(cafeId));
//    }
//
//
//    /**
//     * 메인 카페 상세 조회 API
//     */
//    @GetMapping("/blogs/{cafeNm}")
//    public List<BlogDto> searchBlogList(@PathVariable("cafeNm") String cafeNm) throws IOException {
//        return cafeService.searchBlogList(cafeNm);
//    }
}