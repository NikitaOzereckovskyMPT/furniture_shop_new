package com.web_project.furniture.repository;

import com.web_project.furniture.model.DeliveryPersonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeliveryPersonRepository extends JpaRepository<DeliveryPersonModel, UUID> {
    boolean existsByName(String name);
    Optional<DeliveryPersonModel> findByName(String name);
}