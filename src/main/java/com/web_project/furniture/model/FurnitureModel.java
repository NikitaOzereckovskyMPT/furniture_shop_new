package com.web_project.furniture.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
public class FurnitureModel {
    @Id
    @GeneratedValue
    private UUID id;

    @Size(min = 3, message = "Имя мебели должно содержать не менее 3 символов")
    @Column(unique = true, nullable = false)
    private String name;

    @Size(min = 5, message = "Описание должно содержать не менее 5 символов")
    private String description;

    @DecimalMin(value = "0.0", inclusive = false, message = "Цена должна быть положительной")
    private BigDecimal price;

    @Size(min = 3, message = "Упаковка должна содержать не менее 3 символов")
    private String packaging;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "furniture_type_id", nullable = false)
    private FurnitureTypeModel furnitureType;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "furniture_materials",
            joinColumns = @JoinColumn(name = "furniture_id"),
            inverseJoinColumns = @JoinColumn(name = "material_id")
    )
    private Set<MaterialModel> materials = new HashSet<>();

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public FurnitureTypeModel getFurnitureType() {
        return furnitureType;
    }

    public void setFurnitureType(FurnitureTypeModel furnitureType) {
        this.furnitureType = furnitureType;
    }

    public Set<MaterialModel> getMaterials() {
        return materials;
    }

    public void setMaterials(Set<MaterialModel> materials) {
        this.materials = materials;
    }

    public Set<UUID> getMaterialIds() {
        return materials.stream()
                .map(MaterialModel::getId)
                .collect(Collectors.toSet());
    }

    public void addMaterial(MaterialModel material) {
        materials.add(material);
        material.getFurnitures().add(this);
    }

    public void removeMaterial(MaterialModel material) {
        materials.remove(material);
        material.getFurnitures().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FurnitureModel)) return false;
        FurnitureModel that = (FurnitureModel) o;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}