package com.mbs.api.repository;

import com.mbs.api.entity.AttachEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttachRepository extends JpaRepository<AttachEntity, String> {
    Optional<AttachEntity> findByIdAndVisibleIsTrue(String id);
}