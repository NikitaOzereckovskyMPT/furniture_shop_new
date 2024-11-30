package com.web_project.furniture.repository;

import com.web_project.furniture.model.FurnitureClientModel;
import com.web_project.furniture.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface FurnitureClientRepository extends JpaRepository<FurnitureClientModel, UUID> {
    List<FurnitureClientModel> findByUser(UserModel user);
    List<FurnitureClientModel> findByOrderId(UUID orderId);
    List<FurnitureClientModel> findByUserId(UUID userId);  // Изменено с idUser на id
}