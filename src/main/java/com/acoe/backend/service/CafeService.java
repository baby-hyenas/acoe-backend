package com.acoe.backend.service;

import com.acoe.backend.dto.CafeDto;

import java.io.IOException;
import java.util.List;

public interface CafeService {
    /**
     * 매인 카페 목록 조회(마커용)
     * @param areaCd
     * @param trdStateCd - null이면 상태 관계없이 모든 카페 조회
     * @return
     */

    List<CafeDto> searchDtoList(Long areaCd, Long trdStateCd);
//
//    /**
//     * 메인 카페 목록 조회(목록용)
//     * @param keyword
//     * @return
//     */
//    List<CafeDto> searchDtoListByKeyword(String keyword);
//
//    /**
//     * @param cafeId
//     * @return
//     */
//    CafeDto getCafeDto(Long cafeId);

//    List<BlogDto> searchBlogList(String keyword) throws IOException;
}