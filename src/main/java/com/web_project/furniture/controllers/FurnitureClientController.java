package com.web_project.furniture.controllers;

import com.web_project.furniture.model.*;
import com.web_project.furniture.repository.CustomerRepository;
import com.web_project.furniture.repository.FurnitureClientRepository;
import com.web_project.furniture.repository.OrderRepository;
import com.web_project.furniture.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/furniture-clients")
@PreAuthorize("hasAnyAuthority('MANAGER','ADMIN')")
public class FurnitureClientController {

    private final FurnitureClientRepository furnitureClientRepository;
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public FurnitureClientController(FurnitureClientRepository furnitureClientRepository,
                                     UserRepository userRepository,
                                     CustomerRepository customerRepository,
                                     OrderRepository orderRepository) {
        this.furnitureClientRepository = furnitureClientRepository;
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/all")
    public String getAllFurnitureClients(Model model) {
        model.addAttribute("furnitureClients", furnitureClientRepository.findAll());
        return "all-furniture-clients";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("furnitureClient", new FurnitureClientModel());
        model.addAttribute("users", userRepository.findByRole(RoleEnum.MANAGER));
        model.addAttribute("orders", orderRepository.findAll());
        model.addAttribute("customers", customerRepository.findAll());
        return "add-furniture-client";
    }

    @PostMapping("/add")
    public String addFurnitureClient(@Valid @ModelAttribute("furnitureClient") FurnitureClientModel furnitureClient,
                                     BindingResult result,
                                     Model model) {
        if (result.hasErrors()) {
            model.addAttribute("users", userRepository.findByRole(RoleEnum.MANAGER));
            model.addAttribute("orders", orderRepository.findAll());
            model.addAttribute("customers", customerRepository.findAll());
            return "add-furniture-client";
        }
        furnitureClientRepository.save(furnitureClient);
        return "redirect:/furniture-clients/all";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable UUID id, Model model) {
        FurnitureClientModel furnitureClient = furnitureClientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid furniture client Id:" + id));
        model.addAttribute("furnitureClient", furnitureClient);
        model.addAttribute("users", userRepository.findByRole(RoleEnum.MANAGER));
        model.addAttribute("orders", orderRepository.findAll());
        model.addAttribute("customers", customerRepository.findAll());
        return "edit-furniture-client";
    }

    @PostMapping("/edit/{id}")
    public String updateFurnitureClient(@PathVariable UUID id,
                                        @Valid @ModelAttribute("furnitureClient") FurnitureClientModel furnitureClient,
                                        BindingResult result,
                                        Model model) {
        if (result.hasErrors()) {
            model.addAttribute("users", userRepository.findByRole(RoleEnum.MANAGER));
            model.addAttribute("orders", orderRepository.findAll());
            model.addAttribute("customers", customerRepository.findAll());
            furnitureClient.setId(id);
            return "edit-furniture-client";
        }
        furnitureClient.setId(id);
        furnitureClientRepository.save(furnitureClient);
        return "redirect:/furniture-clients/all";
    }

    @PostMapping("/delete/{id}")
    public String deleteFurnitureClient(@PathVariable UUID id) {
        furnitureClientRepository.deleteById(id);
        return "redirect:/furniture-clients/all";
    }

    @GetMapping("/details/{id}")
    public String getDetails(@PathVariable UUID id, Model model) {
        FurnitureClientModel furnitureClient = furnitureClientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid furniture client Id:" + id));
        model.addAttribute("furnitureClient", furnitureClient);
        return "furniture-client-details";
    }
}