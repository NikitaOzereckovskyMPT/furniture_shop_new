package com.web_project.furniture.service;

import com.web_project.furniture.model.MaterialModel;
import com.web_project.furniture.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;

    @Autowired
    public MaterialServiceImpl(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Override
    public List<MaterialModel> findAllMaterials() {
        return materialRepository.findAll();
    }

    @Override
    public Optional<MaterialModel> findMaterialById(UUID id) {
        return materialRepository.findById(id);
    }

    @Override
    public Optional<MaterialModel> findMaterialByName(String name) {
        return materialRepository.findByName(name);
    }

    @Override
    public MaterialModel addMaterial(MaterialModel material) {
        return materialRepository.save(material);
    }

    @Override
    public MaterialModel updateMaterial(MaterialModel material) {
        if (materialRepository.existsById(material.getId())) {
            return materialRepository.save(material);
        }
        return null;
    }

    @Override
    public void deleteMaterial(UUID id) {
        if (materialRepository.existsById(id)) {
            materialRepository.deleteById(id);
        }
    }
}