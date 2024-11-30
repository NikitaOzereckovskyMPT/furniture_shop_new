package com.web_project.furniture.service;

import com.web_project.furniture.model.FurnitureTypeModel;
import com.web_project.furniture.repository.FurnitureTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FurnitureTypeServiceImpl implements FurnitureTypeService {

    @Autowired
    private FurnitureTypeRepository furnitureTypeRepository;

    @Override
    public List<FurnitureTypeModel> findAllFurnitureTypes() {
        return furnitureTypeRepository.findAll();
    }

    @Override
    public Optional<FurnitureTypeModel> findFurnitureTypeById(UUID id) {
        return furnitureTypeRepository.findById(id);
    }

    @Override
    public Optional<FurnitureTypeModel> findFurnitureTypeByName(String name) {
        return furnitureTypeRepository.findByName(name);
    }

    @Override
    public FurnitureTypeModel addFurnitureType(FurnitureTypeModel furnitureType) {
        return furnitureTypeRepository.save(furnitureType);
    }

    @Override
    public FurnitureTypeModel updateFurnitureType(FurnitureTypeModel furnitureType) {
        return furnitureTypeRepository.save(furnitureType);
    }

    @Override
    public void deleteFurnitureType(UUID id) {
        furnitureTypeRepository.deleteById(id);
    }
}