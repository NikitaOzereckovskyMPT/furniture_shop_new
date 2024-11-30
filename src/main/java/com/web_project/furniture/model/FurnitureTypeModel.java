package com.web_project.furniture.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "furniture_type_model")
public class FurnitureTypeModel {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Size(min = 3, message = "Название типа мебели должно содержать не менее 3 символов")
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Size(min = 5, message = "Описание должно содержать не менее 5 символов")
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "furnitureType", cascade = CascadeType.ALL)
    private Set<FurnitureModel> furnitures = new HashSet<>();

    // Конструкторы
    public FurnitureTypeModel() {}

    public FurnitureTypeModel(UUID id, String name, String description) {
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
        this.furnitures.clear();
        if (furnitures != null) {
            this.furnitures.addAll(furnitures);
            // Обновляем связь с каждым элементом мебели
            for (FurnitureModel furniture : furnitures) {
                furniture.setFurnitureType(this);
            }
        }
    }

    // Вспомогательные методы
    public void addFurniture(FurnitureModel furniture) {
        furnitures.add(furniture);
        furniture.setFurnitureType(this);
    }

    public void removeFurniture(FurnitureModel furniture) {
        furnitures.remove(furniture);
        if (furniture.getFurnitureType() == this) {
            furniture.setFurnitureType(null);
        }
    }

    // equals и hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FurnitureTypeModel)) return false;
        FurnitureTypeModel that = (FurnitureTypeModel) o;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}