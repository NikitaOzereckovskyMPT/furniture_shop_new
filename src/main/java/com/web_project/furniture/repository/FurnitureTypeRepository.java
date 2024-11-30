package com.web_project.furniture.repository;

import com.web_project.furniture.model.FurnitureTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface FurnitureTypeRepository extends JpaRepository<FurnitureTypeModel, UUID> {
    boolean existsByName(String name);
    Optional<FurnitureTypeModel> findByName(String name);
}