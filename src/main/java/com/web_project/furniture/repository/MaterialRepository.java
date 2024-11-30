package com.web_project.furniture.repository;

import com.web_project.furniture.model.MaterialModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface MaterialRepository extends JpaRepository<MaterialModel, UUID> {
    boolean existsByName(String name);
    Optional<MaterialModel> findByName(String name);
}