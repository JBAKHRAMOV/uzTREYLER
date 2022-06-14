package com.company.repository;

import com.company.entity.KinoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface KinoRepository extends JpaRepository<KinoEntity, String> {
    Optional<KinoEntity> findByIdAndDeletedDateIsNull(String trailerId);


    Page<KinoEntity> findAllByDeletedDateIsNull(Pageable pageable);


    @Modifying
    @Transactional
    @Query("update KinoEntity  set  deletedDate = ?2 where  id = ?1")
    void updateDeleteDate(String trailerId, LocalDateTime deletedDate);

    Page<KinoEntity> findByCategoryIdAndDeletedDateIsNull(String categoryID, Pageable pageable);
    Page<KinoEntity> findByNameAndDeletedDateIsNull(String name, Pageable pageable);



}