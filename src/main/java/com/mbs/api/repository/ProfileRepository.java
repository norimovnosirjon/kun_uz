package com.mbs.api.repository;

import com.mbs.api.entity.ProfileEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity, String> {
    Optional<ProfileEntity> findByEmailAndVisibleIsTrue(String email);

    @Modifying
    @Transactional
    @Query(value = "update ProfileEntity set status = 'ACTIVE' where email = ?1")
    void updateStatus(String email);
}