package com.web_project.furniture.controllers;

import com.web_project.furniture.model.CustomerModel;
import com.web_project.furniture.service.CustomerService;
import com.web_project.furniture.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/customers")
@PreAuthorize("hasAnyAuthority('ADMIN','CUSTOMER')")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    // Показать список всех клиентов
    @GetMapping("/all")
    public String getAllCustomers(Model model) {
        model.addAttribute("customers", customerService.findAllCustomers());
        return "all-customers";
    }

    // Форма добавления нового клиента
    @GetMapping("/add")
    public String showAddCustomerForm(Model model) {
        model.addAttribute("customer", new CustomerModel());
        return "AddCustomer";
    }

    // Обработка добавления клиента
    @PostMapping("/add")
    public String addCustomer(@Valid @ModelAttribute("customer") CustomerModel customer,
                              BindingResult result,
                              Model model) {
        if (result.hasErrors()) {
            return "AddCustomer";
        }
        customerService.addCustomer(customer);
        return "redirect:/customers/all";
    }

    // Показать детали клиента
    @GetMapping("/{id}/details")
    public String getCustomerDetails(@PathVariable("id") UUID id, Model model) {
        CustomerModel customer = customerService.findCustomerById(id)
                .orElseThrow(() -> new EntityNotFoundException("Клиент не найден"));
        model.addAttribute("customer", customer);
        return "CustomerDetails";
    }

    // Форма редактирования клиента
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable UUID id, Model model) {
        CustomerModel customer = customerService.findCustomerById(id)
                .orElseThrow(() -> new EntityNotFoundException("Клиент не найден"));
        model.addAttribute("customer", customer);
        return "EditCustomer";
    }

    // Обработка обновления клиента
    @PostMapping("/{id}/update")
    public String updateCustomer(@PathVariable UUID id,
                                 @Valid @ModelAttribute("customer") CustomerModel customer,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return "EditCustomer";
        }
        customer.setId(id);
        CustomerModel updatedCustomer = customerService.updateCustomer(customer);
        if (updatedCustomer == null) {
            throw new EntityNotFoundException("Клиент не найден");
        }
        return "redirect:/customers/all";
    }

    // Удаление клиента
    @PostMapping("/{id}/delete")
    public String deleteCustomer(@PathVariable UUID id) {
        customerService.deleteCustomer(id);
        return "redirect:/customers/all";
    }
}