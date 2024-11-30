package com.web_project.furniture.service;

import com.web_project.furniture.model.OrderModel;
import com.web_project.furniture.model.UserModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {
    List<OrderModel> findAllOrders();
    List<OrderModel> findOrdersByUser(UserModel user);
    OrderModel createOrder(OrderModel order, UserModel user);
    OrderModel updateOrder(OrderModel order);
    void deleteOrder(UUID id);

    Optional<OrderModel> findOrderById(UUID id);

    void addOrder(OrderModel order);
}