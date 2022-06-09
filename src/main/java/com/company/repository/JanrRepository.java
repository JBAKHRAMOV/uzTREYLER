package com.company.repository;

import com.company.entity.JanrEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JanrRepository extends JpaRepository<JanrEntity, String> {


    Optional<JanrEntity> findByName(String name);

}