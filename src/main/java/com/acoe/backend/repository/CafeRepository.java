package com.acoe.backend.repository;

import com.acoe.backend.entity.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CafeRepository extends JpaRepository<Cafe, Long> {
    List<Cafe> findByAreaCd(Long areaCd);
    List<Cafe> findByAreaCdAndTrdStateCd(Long areaCd, Long trdStateCd);
    List<Cafe> findByCafeNmContains(String keyword);
}