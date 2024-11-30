package com.web_project.furniture.repository;

import com.web_project.furniture.model.FurnitureModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface FurnitureRepository extends JpaRepository<FurnitureModel, UUID> {
    boolean existsByName(String name);
    Optional<FurnitureModel> findByName(String name);
}