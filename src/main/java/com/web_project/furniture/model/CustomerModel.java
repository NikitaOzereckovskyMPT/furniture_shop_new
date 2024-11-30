package com.web_project.furniture.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "customers")
public class CustomerModel {
    @Id
    @GeneratedValue
    private UUID id;

    @Size(min = 3, message = "Имя должно содержать не менее 3 символов")
    @Column(nullable = false)
    private String name;

    @Size(min = 10, max = 15, message = "Телефон должен содержать от 10 до 15 цифр")
    @Column(nullable = false)
    private String phone;

    @Email(message = "Email должен быть корректным")
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<FurnitureClientModel> furnitureClients = new HashSet<>();

    // Конструкторы
    public CustomerModel() {}

    public CustomerModel(UUID id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<FurnitureClientModel> getFurnitureClients() {
        return furnitureClients;
    }

    public void setFurnitureClients(Set<FurnitureClientModel> furnitureClients) {
        this.furnitureClients.clear();
        if (furnitureClients != null) {
            this.furnitureClients.addAll(furnitureClients);
        }
    }

    // Вспомогательные методы
    public void addFurnitureClient(FurnitureClientModel furnitureClient) {
        furnitureClients.add(furnitureClient);
        furnitureClient.setCustomer(this);
    }

    public void removeFurnitureClient(FurnitureClientModel furnitureClient) {
        furnitureClients.remove(furnitureClient);
        if (furnitureClient.getCustomer() == this) {
            furnitureClient.setCustomer(null);
        }
    }

    // equals и hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerModel)) return false;
        CustomerModel that = (CustomerModel) o;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}