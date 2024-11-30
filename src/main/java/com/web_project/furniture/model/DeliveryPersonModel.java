package com.web_project.furniture.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "delivery_persons")
public class DeliveryPersonModel {
    @Id
    @GeneratedValue
    private UUID id;

    @Size(min = 3, message = "Имя должно содержать не менее 3 символов")
    @Column(nullable = false)
    private String name;

    @Size(min = 10, max = 15, message = "Телефон должен содержать от 10 до 15 цифр")
    @Column(nullable = false)
    private String phone;

    @OneToMany(mappedBy = "deliveryPerson", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DeliveryModel> deliveries = new HashSet<>();

    // Конструкторы
    public DeliveryPersonModel() {}

    public DeliveryPersonModel(UUID id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    // Геттеры и сеттеры
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<DeliveryModel> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(Set<DeliveryModel> deliveries) {
        this.deliveries = deliveries;
    }

    // Вспомогательные методы
    public void addDelivery(DeliveryModel delivery) {
        deliveries.add(delivery);
        delivery.setDeliveryPerson(this);
    }

    public void removeDelivery(DeliveryModel delivery) {
        deliveries.remove(delivery);
        delivery.setDeliveryPerson(null);
    }
}