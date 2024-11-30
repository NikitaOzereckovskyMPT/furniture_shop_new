package com.web_project.furniture.service;

import com.web_project.furniture.model.FurnitureModel;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FurnitureService {
    List<FurnitureModel> findAllFurnitures();
    Optional<FurnitureModel> findFurnitureById(UUID id);
    Optional<FurnitureModel> findFurnitureByName(String name);
    FurnitureModel addFurniture(FurnitureModel furniture);
    FurnitureModel updateFurniture(FurnitureModel furniture);
    void deleteFurniture(UUID id);
}
