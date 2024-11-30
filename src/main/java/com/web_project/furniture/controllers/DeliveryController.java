package com.web_project.furniture.controllers;

import com.web_project.furniture.model.DeliveryModel;
import com.web_project.furniture.service.DeliveryPersonService;
import com.web_project.furniture.service.DeliveryService;
import com.web_project.furniture.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/deliveries")
@PreAuthorize("hasAnyAuthority('MANAGER','ADMIN')")
public class DeliveryController {

    private final DeliveryService deliveryService;
    private final DeliveryPersonService deliveryPersonService;
    private final OrderService orderService;

    @Autowired
    public DeliveryController(DeliveryService deliveryService,
                              DeliveryPersonService deliveryPersonService,
                              OrderService orderService) {
        this.deliveryService = deliveryService;
        this.deliveryPersonService = deliveryPersonService;
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public String getAllDeliveries(Model model) {
        model.addAttribute("deliveries", deliveryService.findAllDeliveries());
        return "all-deliveries"; // Изменено название шаблона
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("delivery", new DeliveryModel());
        model.addAttribute("deliveryPersons", deliveryPersonService.findAllDeliveryPersons());
        model.addAttribute("orders", orderService.findAllOrders());
        return "add-delivery";
    }

    @PostMapping("/add")
    public String addDelivery(@Valid @ModelAttribute("delivery") DeliveryModel delivery,
                              BindingResult result,
                              Model model) {
        if (result.hasErrors()) {
            model.addAttribute("deliveryPersons", deliveryPersonService.findAllDeliveryPersons());
            model.addAttribute("orders", orderService.findAllOrders());
            return "add-delivery";
        }
        deliveryService.addDelivery(delivery);
        return "redirect:/deliveries/all";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable UUID id, Model model) {
        DeliveryModel delivery = deliveryService.findDeliveryById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid delivery Id:" + id));
        model.addAttribute("delivery", delivery);
        model.addAttribute("deliveryPersons", deliveryPersonService.findAllDeliveryPersons());
        model.addAttribute("orders", orderService.findAllOrders());
        return "edit-delivery";
    }

    @PostMapping("/{id}/update")
    public String updateDelivery(@PathVariable UUID id,
                                 @Valid @ModelAttribute("delivery") DeliveryModel delivery,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            delivery.setId(id);
            model.addAttribute("deliveryPersons", deliveryPersonService.findAllDeliveryPersons());
            model.addAttribute("orders", orderService.findAllOrders());
            return "edit-delivery";
        }
        delivery.setId(id);
        deliveryService.updateDelivery(delivery);
        return "redirect:/deliveries/all";
    }

    @PostMapping("/{id}/delete")
    public String deleteDelivery(@PathVariable UUID id) {
        deliveryService.deleteDelivery(id);
        return "redirect:/deliveries/all";
    }

    @GetMapping("/{id}/details")
    public String getDetails(@PathVariable UUID id, Model model) {
        DeliveryModel delivery = deliveryService.findDeliveryById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid delivery Id:" + id));
        model.addAttribute("delivery", delivery);
        return "delivery-details";
    }
}