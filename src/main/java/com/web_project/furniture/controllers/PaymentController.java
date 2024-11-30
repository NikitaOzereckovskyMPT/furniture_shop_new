package com.web_project.furniture.controllers;

import com.web_project.furniture.model.PaymentModel;
import com.web_project.furniture.service.OrderService;
import com.web_project.furniture.service.PaymentService;
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
@RequestMapping("/payments")
@PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderService orderService;

    // Список всех платежей
    @GetMapping("/all")
    public String getAllPayments(Model model) {
        model.addAttribute("payments", paymentService.findAllPayments());
        return "all-payments"; // Изменен путь
    }

    // Форма добавления платежа
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("payment", new PaymentModel());
        model.addAttribute("orders", orderService.findAllOrders());
        return "add-payment"; // Изменен путь
    }

    // Обработка добавления платежа
    @PostMapping("/add")
    public String addPayment(@Valid @ModelAttribute("payment") PaymentModel payment,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("orders", orderService.findAllOrders());
            return "add-payment"; // Изменен путь
        }
        paymentService.addPayment(payment);
        return "redirect:/payments/all";
    }

    // Форма редактирования платежа
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable UUID id, Model model) {
        PaymentModel payment = paymentService.findPaymentById(id)
                .orElseThrow(() -> new EntityNotFoundException("Платеж не найден"));
        model.addAttribute("payment", payment);
        model.addAttribute("orders", orderService.findAllOrders());
        return "edit-payment"; // Изменен путь
    }

    // Обработка обновления платежа
    @PostMapping("/{id}/update")
    public String updatePayment(@PathVariable UUID id,
                                @Valid @ModelAttribute("payment") PaymentModel payment,
                                BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            model.addAttribute("orders", orderService.findAllOrders());
            return "edit-payment"; // Изменен путь
        }
        payment.setId(id);
        PaymentModel updatedPayment = paymentService.updatePayment(payment);
        if (updatedPayment == null) {
            throw new EntityNotFoundException("Платеж не найден");
        }
        return "redirect:/payments/all";
    }

    // Удаление платежа
    @PostMapping("/{id}/delete")
    public String deletePayment(@PathVariable UUID id) {
        paymentService.deletePayment(id);
        return "redirect:/payments/all";
    }

    // Просмотр деталей платежа
    @GetMapping("/{id}/details")
    public String showDetails(@PathVariable UUID id, Model model) {
        PaymentModel payment = paymentService.findPaymentById(id)
                .orElseThrow(() -> new EntityNotFoundException("Платеж не найден"));
        model.addAttribute("payment", payment);
        return "payment-details"; // Изменен путь
    }
}