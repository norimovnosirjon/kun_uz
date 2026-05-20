package com.mbs.api.repository;

import com.mbs.api.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {
    Optional<CategoryEntity> findByIdAndVisibleIsTrue(String id);
}