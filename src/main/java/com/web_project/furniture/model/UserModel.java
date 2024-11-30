package com.web_project.furniture.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user_model")
public class UserModel {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @NotBlank(message = "Логин не может быть пустым")
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 3, message = "Пароль не менее 3 символов")
    private String password;

    private boolean active;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleEnum role; // Изменено с Set<RoleEnum> на RoleEnum

    @Size(min = 2, message = "ФИО должно содержать не менее 2 символов")
    @Column(name = "full_name")
    private String fullName;

    @Size(min = 10, max = 15, message = "Телефон должен содержать от 10 до 15 цифр")
    @Column(name = "phone")
    private String phone;

    @Email(message = "Email должен быть корректным")
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<OrderModel> orders = new HashSet<>();

    // Конструкторы
    public UserModel() {}

    // Геттеры и сеттеры
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public Set<OrderModel> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderModel> orders) {
        this.orders = orders;
    }

    // Вспомогательные методы
    public void addOrder(OrderModel order) {
        orders.add(order);
        order.setUser(this);
    }

    public void removeOrder(OrderModel order) {
        orders.remove(order);
        if (order.getUser() == this) {
            order.setUser(null);
        }
    }
}