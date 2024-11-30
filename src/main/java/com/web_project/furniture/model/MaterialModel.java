package com.web_project.furniture.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "materials")
public class MaterialModel {
    @Id
    @GeneratedValue
    private UUID id;

    @Size(min = 3, message = "Название материала должно содержать не менее 3 символов")
    @Column(unique = true, nullable = false)
    private String name;

    @Size(min = 5, message = "Описание должно содержать не менее 5 символов")
    private String description;

    @ManyToMany(mappedBy = "materials")
    private Set<FurnitureModel> furnitures = new HashSet<>();

    // Конструкторы
    public MaterialModel() {}

    public MaterialModel(UUID id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<FurnitureModel> getFurnitures() {
        return furnitures;
    }

    public void setFurnitures(Set<FurnitureModel> furnitures) {
        this.furnitures = furnitures;
    }

    // Вспомогательные методы
    public void addFurniture(FurnitureModel furniture) {
        this.furnitures.add(furniture);
        furniture.getMaterials().add(this);
    }

    public void removeFurniture(FurnitureModel furniture) {
        this.furnitures.remove(furniture);
        furniture.getMaterials().remove(this);
    }
}