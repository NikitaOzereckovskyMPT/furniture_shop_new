package com.web_project.furniture.service;

import com.web_project.furniture.model.FurnitureTypeModel;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FurnitureTypeService {
    List<FurnitureTypeModel> findAllFurnitureTypes();
    Optional<FurnitureTypeModel> findFurnitureTypeById(UUID id);
    Optional<FurnitureTypeModel> findFurnitureTypeByName(String name);
    FurnitureTypeModel addFurnitureType(FurnitureTypeModel furnitureType);
    FurnitureTypeModel updateFurnitureType(FurnitureTypeModel furnitureType);
    void deleteFurnitureType(UUID id);
}