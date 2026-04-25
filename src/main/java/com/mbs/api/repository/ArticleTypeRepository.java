package com.mbs.api.repository;

import com.mbs.api.entity.ArticleTypeEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author 'Bilol Tuxtamurodov' on 14.04.2026
 * @project Lesson_134_kun_uz
 * @contact @BilolTuxtamurodov
 */
public interface ArticleTypeRepository extends JpaRepository<ArticleTypeEntity, String> {
    Optional<ArticleTypeEntity> findByNameUzAndVisibleIsTrue(String nameUz);
    Optional<ArticleTypeEntity> findByIdAndVisibleIsTrue(String id);

    List<ArticleTypeEntity> findAllByVisibleIsTrue();

    @Modifying
    @Transactional
    @Query(value = "update ArticleTypeEntity set nameUz =?2, nameEn =?3, nameRu = ?4 where id =?1")
    int updateNames(String id, String nUz, String nEn, String nRu);
    @Modifying
    @Transactional
    @Query(value = "update ArticleTypeEntity set visible = ?2 where id =?1")
    int updateVisible(String id, Boolean visible);
}
