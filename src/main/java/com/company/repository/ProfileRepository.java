package com.company.repository;

import com.company.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity, String> {

    Optional<ProfileEntity> findByEmailAndDeletedDateIsNull(String email);
    Optional<ProfileEntity> findByIdAndDeletedDateIsNull(String id);

    @Transactional
    @Modifying
    @Query(value = "update ProfileEntity as p set p.password=:pass where p.id=:id")
    void changePassword(String id, String pass);

    @Transactional
    @Modifying
    @Query(value = "update ProfileEntity as p set p.email=:newEmail where p.id=:id")
    void changeEmail(String id, String newEmail);
}