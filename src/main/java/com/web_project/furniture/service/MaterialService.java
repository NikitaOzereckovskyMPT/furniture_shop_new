package com.web_project.furniture.service;

import com.web_project.furniture.model.MaterialModel;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MaterialService {
    List<MaterialModel> findAllMaterials();
    Optional<MaterialModel> findMaterialById(UUID id);
    Optional<MaterialModel> findMaterialByName(String name);
    MaterialModel addMaterial(MaterialModel material);
    MaterialModel updateMaterial(MaterialModel material);
    void deleteMaterial(UUID id);
}