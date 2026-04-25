package com.mbs.api.repository;

import com.mbs.api.entity.SmsEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SmsRepository extends JpaRepository<SmsEntity, String> {
    Optional<SmsEntity> findTop1ByEmailAndUsedIsFalseOrderByCreatedDateDesc(String email);

    @Modifying
    @Transactional
    @Query(value = "update SmsEntity set used = true where id = ?1")
    void updateUsedById(String id);

}