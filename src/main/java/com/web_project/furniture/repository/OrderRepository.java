package com.web_project.furniture.repository;

import com.web_project.furniture.model.OrderModel;
import com.web_project.furniture.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderModel, UUID> {
    List<OrderModel> findByUser(UserModel user);
}