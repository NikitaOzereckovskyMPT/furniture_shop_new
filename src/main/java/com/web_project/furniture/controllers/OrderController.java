package com.web_project.furniture.controllers;

import com.web_project.furniture.model.OrderModel;
import com.web_project.furniture.service.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/orders")
@PreAuthorize("hasAnyAuthority('ADMIN','CUSTOMER')")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private FurnitureClientService furnitureClientService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DeliveryService deliveryService;

    @GetMapping("/all")
    public String getAllOrders(Model model) {
        List<OrderModel> orders = orderService.findAllOrders();
        model.addAttribute("orders", orders);
        return "all-orders";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("order", new OrderModel());
        model.addAttribute("furnitureClients", furnitureClientService.findAllFurnitureClients());
        model.addAttribute("customers", customerService.findAllCustomers());
        model.addAttribute("deliveries", deliveryService.findAllDeliveries());
        return "add-order";
    }

    @PostMapping("/add")
    public String addOrder(@Valid @ModelAttribute("order") OrderModel order,
                           @RequestParam("customerId") UUID customerId,
                           @RequestParam("deliveryId") UUID deliveryId,
                           BindingResult result,
                           Model model) {
        if (!result.hasErrors()) {
            order.setCustomer(customerService.findCustomerById(customerId).orElseThrow());
            order.setDelivery(deliveryService.findDeliveryById(deliveryId).orElseThrow());
            orderService.addOrder(order);
            return "redirect:/orders/all";
        } else {
            model.addAttribute("furnitureClients", furnitureClientService.findAllFurnitureClients());
            model.addAttribute("customers", customerService.findAllCustomers());
            model.addAttribute("deliveries", deliveryService.findAllDeliveries());
            return "add-order";
        }
    }

    // Форма редактирования заказа
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable UUID id, Model model) {
        Optional<OrderModel> optionalOrder = orderService.findOrderById(id);
        if (optionalOrder.isPresent()) {
            model.addAttribute("order", optionalOrder.get());
            model.addAttribute("furnitureClients", furnitureClientService.findAllFurnitureClients());
            model.addAttribute("customers", customerService.findAllCustomers());
            model.addAttribute("deliveries", deliveryService.findAllDeliveries());
            return "edit-order";
        } else {
            throw new EntityNotFoundException("Заказ не найден");
        }
    }

    // Обработка обновления заказа
    @PostMapping("/{id}/update")
    public String updateOrder(@PathVariable UUID id,
                              @Valid @ModelAttribute("order") OrderModel order,
                              @RequestParam("customerId") UUID customerId,
                              @RequestParam("deliveryId") UUID deliveryId,
                              BindingResult result,
                              Model model) {
        Optional<OrderModel> optionalOrder = orderService.findOrderById(id);
        if (optionalOrder.isPresent()) {
            if (result.hasErrors()) {
                model.addAttribute("furnitureClients", furnitureClientService.findAllFurnitureClients());
                model.addAttribute("customers", customerService.findAllCustomers());
                model.addAttribute("deliveries", deliveryService.findAllDeliveries());
                order.setId(id);
                return "edit-order";
            }
            order.setId(id);
            order.setCustomer(customerService.findCustomerById(customerId).orElseThrow());
            order.setDelivery(deliveryService.findDeliveryById(deliveryId).orElseThrow());
            OrderModel updatedOrder = orderService.updateOrder(order);
            if (updatedOrder == null) {
                throw new EntityNotFoundException("Заказ не найден");
            }
        } else {
            throw new EntityNotFoundException("Заказ не найден");
        }
        return "redirect:/orders/all";
    }

    // Удаление заказа
    @PostMapping("/{id}/delete")
    public String deleteOrder(@PathVariable UUID id) {
        orderService.deleteOrder(id);
        return "redirect:/orders/all";
    }

    // Просмотр деталей заказа
    @GetMapping("/{id}/details")
    public String showDetails(@PathVariable UUID id, Model model) {
        Optional<OrderModel> optionalOrder = orderService.findOrderById(id);
        if (optionalOrder.isPresent()) {
            model.addAttribute("order", optionalOrder.get());
            return "order-details";
        } else {
            throw new EntityNotFoundException("Заказ не найден");
        }
    }
}