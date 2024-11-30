package com.web_project.furniture.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class PaymentModel {
    @Id
    @GeneratedValue
    private UUID id;

    private LocalDate paymentDate;

    @Size(min = 3, message = "Метод оплаты должен содержать не менее 3 символов")
    @Column(nullable = false)
    private String paymentMethod;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderModel order;

    // Конструкторы
    public PaymentModel() {}

    public PaymentModel(UUID id, LocalDate paymentDate, String paymentMethod, OrderModel order) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.order = order;
    }

    // Геттеры и сеттеры

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Size(min = 3, message = "Метод оплаты должен содержать не менее 3 символов")
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(@Size(min = 3, message = "Метод оплаты должен содержать не менее 3 символов") String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }
}
