package com.acoe.backend.repository;

import com.acoe.backend.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByCafe_CafeId(Long cafeId);
    List<Menu> findByFranchise_FranchiseId(Long franchiseId);
    void deleteAllByFranchise_FranchiseId(Long franchiseId);
    void deleteAllByCafe_CafeId(Long cafeId);
}