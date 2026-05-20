package com.mbs.api.repository;

import com.mbs.api.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<ArticleEntity, String> {
    @Query(value = "from ArticleEntity as a join fetch a.region join fetch a.category where a.visible is true and a.id = ?1")
    Optional<ArticleEntity> findByIdAndVisibleIsTrue(String id);
}