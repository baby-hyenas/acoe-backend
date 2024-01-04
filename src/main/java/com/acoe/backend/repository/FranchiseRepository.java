package com.acoe.backend.repository;

import com.acoe.backend.entity.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FranchiseRepository extends JpaRepository<Franchise, Long> {
    List<Franchise> findAllByUseYn(Boolean useYn);
}