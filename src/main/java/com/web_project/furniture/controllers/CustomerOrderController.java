package com.web_project.furniture.controllers;

import com.web_project.furniture.model.OrderModel;
import com.web_project.furniture.model.UserModel;
import com.web_project.furniture.service.OrderService;
import com.web_project.furniture.service.FurnitureService;
import com.web_project.furniture.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/customer/orders")
@PreAuthorize("hasAuthority('CUSTOMER')")
public class CustomerOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private FurnitureService furnitureService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/my")
    public String getMyOrders(Model model, Authentication authentication) {
        UserModel user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
        model.addAttribute("orders", orderService.findOrdersByUser(user));
        return "customer-my-orders";
    }

    @GetMapping("/create")
    public String showCreateOrderForm(Model model) {
        model.addAttribute("order", new OrderModel());
        model.addAttribute("furnitureList", furnitureService.findAllFurnitures());
        return "customer-create-order";
    }

    @PostMapping("/create")
    public String createOrder(@Valid @ModelAttribute("order") OrderModel order,
                              BindingResult result,
                              Authentication authentication) {
        if (result.hasErrors()) {
            return "customer-create-order";
        }

        UserModel user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        orderService.createOrder(order, user);
        return "redirect:/customer/orders/my";
    }

    @GetMapping("/{id}/details")
    public String viewOrderDetails(@PathVariable UUID id, Model model, Authentication authentication) {
        UserModel user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        OrderModel order = (OrderModel) orderService.findOrderById(id)
                .orElseThrow(() -> new EntityNotFoundException("Заказ не найден"));

        if (!order.getUser().equals(user)) {
            throw new AccessDeniedException("У вас нет доступа к этому заказу");
        }

        model.addAttribute("order", order);
        return "customer-order-details";
    }
}