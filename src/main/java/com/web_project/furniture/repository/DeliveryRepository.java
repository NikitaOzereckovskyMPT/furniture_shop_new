package com.web_project.furniture.repository;

import com.web_project.furniture.model.DeliveryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeliveryRepository extends JpaRepository<DeliveryModel, UUID> {
    boolean existsByStatus(String status);
    Optional<DeliveryModel> findByStatus(String status);
}