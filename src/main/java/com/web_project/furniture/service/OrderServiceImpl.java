package com.web_project.furniture.service;

import com.web_project.furniture.model.OrderModel;
import com.web_project.furniture.model.UserModel;
import com.web_project.furniture.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<OrderModel> findAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<OrderModel> findOrdersByUser(UserModel user) {
        return orderRepository.findByUser(user);
    }

    @Override
    public OrderModel createOrder(OrderModel order, UserModel user) {
        order.setUser(user);
        return orderRepository.save(order);
    }

    @Override
    public OrderModel updateOrder(OrderModel order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(UUID id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Optional<OrderModel> findOrderById(UUID id) {
        return orderRepository.findById(id);
    }

    @Override
    public void addOrder(OrderModel order) {
        orderRepository.save(order);
    }
}