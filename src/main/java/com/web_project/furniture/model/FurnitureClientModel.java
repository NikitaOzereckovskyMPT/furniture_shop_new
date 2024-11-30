package com.web_project.furniture.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(name = "furniture_clients")
public class FurnitureClientModel {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Size(min = 3, message = "Название первого предмета должно содержать не менее 3 символов")
    @Column(nullable = false)
    private String firstFurniture;

    @Size(min = 3, message = "Название второго предмета должно содержать не менее 3 символов")
    @Column(nullable = false)
    private String secondFurniture;

    @Size(min = 3, message = "Название третьего предмета должно содержать не менее 3 символов")
    private String thirdFurniture;

    @Size(min = 3, message = "Название четвертого предмета должно содержать не менее 3 символов")
    private String fourthFurniture;

    @Size(min = 3, message = "Упаковка должна содержать не менее 3 символов")
    private String packaging;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderModel order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserModel user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerModel customer;

    // Конструкторы
    public FurnitureClientModel() {}

    public FurnitureClientModel(String firstFurniture, String secondFurniture,
                                String thirdFurniture, String fourthFurniture,
                                String packaging, OrderModel order, UserModel user,
                                CustomerModel customer) {
        this.firstFurniture = firstFurniture;
        this.secondFurniture = secondFurniture;
        this.thirdFurniture = thirdFurniture;
        this.fourthFurniture = fourthFurniture;
        this.packaging = packaging;
        this.order = order;
        this.user = user;
        this.customer = customer;
    }

    // Геттеры и сеттеры
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstFurniture() {
        return firstFurniture;
    }

    public void setFirstFurniture(String firstFurniture) {
        this.firstFurniture = firstFurniture;
    }

    public String getSecondFurniture() {
        return secondFurniture;
    }

    public void setSecondFurniture(String secondFurniture) {
        this.secondFurniture = secondFurniture;
    }

    public String getThirdFurniture() {
        return thirdFurniture;
    }

    public void setThirdFurniture(String thirdFurniture) {
        this.thirdFurniture = thirdFurniture;
    }

    public String getFourthFurniture() {
        return fourthFurniture;
    }

    public void setFourthFurniture(String fourthFurniture) {
        this.fourthFurniture = fourthFurniture;
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    // equals и hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FurnitureClientModel)) return false;
        FurnitureClientModel that = (FurnitureClientModel) o;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}