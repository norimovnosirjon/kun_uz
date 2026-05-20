package com.mbs.api.repository;

import com.mbs.api.entity.RegionEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author 'Bilol Tuxtamurodov' on 20.05.2026
 * @project Lesson_134_kun_uz
 * @contact @BilolTuxtamurodov
 */
public interface RegionRepository extends JpaRepository<RegionEntity, String> {
    Optional<RegionEntity> findByNameUzAndOrderNumberAndVisibleIsTrue(String nameUz, Integer orderNumber);
    Optional<RegionEntity> findByIdAndVisibleIsTrue(String id);
    List<RegionEntity> findAllByVisibleIsTrue();

    @Modifying
    @Transactional
    @Query(value = "update RegionEntity set visible = ?2 where id = ?1")
    int updateVisible(String id, Boolean visible);
}
