package com.web_project.furniture.controllers;

import com.web_project.furniture.model.DeliveryPersonModel;
import com.web_project.furniture.service.DeliveryPersonService;
import com.web_project.furniture.service.DeliveryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/delivery-persons")
@PreAuthorize("hasAnyAuthority('MANAGER','ADMIN')")
public class DeliveryPersonController {

    private final DeliveryPersonService deliveryPersonService;
    private final DeliveryService deliveryService;

    @Autowired
    public DeliveryPersonController(DeliveryPersonService deliveryPersonService,
                                    DeliveryService deliveryService) {
        this.deliveryPersonService = deliveryPersonService;
        this.deliveryService = deliveryService;
    }

    @GetMapping("/all")
    public String getAllDeliveryPersons(Model model) {
        model.addAttribute("deliveryPersons", deliveryPersonService.findAllDeliveryPersons());
        return "all-delivery-persons"; // Изменено название шаблона
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("deliveryPerson", new DeliveryPersonModel());
        return "add-delivery-person";
    }

    @PostMapping("/add")
    public String addDeliveryPerson(@Valid @ModelAttribute("deliveryPerson") DeliveryPersonModel deliveryPerson,
                                    BindingResult result) {
        if (result.hasErrors()) {
            return "add-delivery-person";
        }
        deliveryPersonService.addDeliveryPerson(deliveryPerson);
        return "redirect:/delivery-persons/all";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable UUID id, Model model) {
        DeliveryPersonModel deliveryPerson = deliveryPersonService.findDeliveryPersonById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid delivery person Id:" + id));
        model.addAttribute("deliveryPerson", deliveryPerson);
        return "edit-delivery-person";
    }

    @PostMapping("/{id}/update")
    public String updateDeliveryPerson(@PathVariable UUID id,
                                       @Valid @ModelAttribute("deliveryPerson") DeliveryPersonModel deliveryPerson,
                                       BindingResult result) {
        if (result.hasErrors()) {
            deliveryPerson.setId(id);
            return "edit-delivery-person";
        }
        deliveryPerson.setId(id);
        deliveryPersonService.updateDeliveryPerson(deliveryPerson);
        return "redirect:/delivery-persons/all";
    }

    @PostMapping("/{id}/delete")
    public String deleteDeliveryPerson(@PathVariable UUID id) {
        deliveryPersonService.deleteDeliveryPerson(id);
        return "redirect:/delivery-persons/all";
    }

    @GetMapping("/{id}/details")
    public String getDetails(@PathVariable UUID id, Model model) {
        DeliveryPersonModel deliveryPerson = deliveryPersonService.findDeliveryPersonById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid delivery person Id:" + id));
        model.addAttribute("deliveryPerson", deliveryPerson);
        return "delivery-person-details";
    }
}
