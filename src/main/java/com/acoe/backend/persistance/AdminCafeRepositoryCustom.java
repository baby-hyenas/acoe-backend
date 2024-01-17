package com.acoe.backend.persistance;

import com.acoe.backend.dto.AdminCafeSearchDto;
import com.acoe.backend.entity.Cafe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminCafeRepositoryCustom {
    Page<Cafe> searchListByDynamicCond(AdminCafeSearchDto searchDto, Pageable pageable);
}
