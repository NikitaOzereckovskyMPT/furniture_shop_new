package com.web_project.furniture.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "deliveries")
public class DeliveryModel {
    @Id
    @GeneratedValue
    private UUID id;

    @NotNull(message = "Дата доставки не может быть пустой")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Size(min = 3, message = "Статус должен содержать не менее 3 символов")
    @Column(nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_person_id", nullable = false)
    private DeliveryPersonModel deliveryPerson;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderModel order;

    // Конструкторы
    public DeliveryModel() {}

    public DeliveryModel(UUID id, LocalDate date, String status,
                         DeliveryPersonModel deliveryPerson, OrderModel order) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.deliveryPerson = deliveryPerson;
        this.order = order;
    }

    // Геттеры и сеттеры
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DeliveryPersonModel getDeliveryPerson() {
        return deliveryPerson;
    }

    public void setDeliveryPerson(DeliveryPersonModel deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
    }

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }
}