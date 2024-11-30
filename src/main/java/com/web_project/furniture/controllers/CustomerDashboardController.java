package com.web_project.furniture.controllers;

import com.web_project.furniture.model.UserModel;
import com.web_project.furniture.model.OrderModel;
import com.web_project.furniture.service.OrderService;
import com.web_project.furniture.service.FurnitureService;
import com.web_project.furniture.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
@PreAuthorize("hasAuthority('CUSTOMER')")
public class CustomerDashboardController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private FurnitureService furnitureService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/home")
    public String dashboard(Model model, Authentication authentication) {
        UserModel user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new IllegalStateException("Пользователь не найден"));

        model.addAttribute("latestOrders", orderService.findOrdersByUser(user));
        return "customer-dashboard"; // было "customer/dashboard"
    }

    @GetMapping("/catalog")
    public String catalog(Model model) {
        model.addAttribute("furniture", furnitureService.findAllFurnitures());
        return "customer-catalog"; // было "customer/catalog"
    }

    @GetMapping("/profile")
    public String profile(Model model, Authentication authentication) {
        UserModel user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new IllegalStateException("Пользователь не найден"));

        model.addAttribute("user", user);
        return "customer-profile"; // было "customer/profile"
    }
}