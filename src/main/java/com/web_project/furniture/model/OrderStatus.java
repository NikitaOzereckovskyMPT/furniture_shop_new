package com.web_project.furniture.model;

public enum OrderStatus {
    NEW("Новый"),
    PROCESSING("В обработке"),
    PAID("Оплачен"),
    SHIPPING("Доставляется"),
    COMPLETED("Завершен"),
    CANCELLED("Отменен");

    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}