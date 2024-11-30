package com.web_project.furniture.service;

import com.web_project.furniture.model.FurnitureModel;
import com.web_project.furniture.repository.FurnitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FurnitureServiceImpl implements FurnitureService {

    private final FurnitureRepository furnitureRepository;

    @Autowired
    public FurnitureServiceImpl(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    @Override
    public List<FurnitureModel> findAllFurnitures() {
        return furnitureRepository.findAll();
    }

    @Override
    public Optional<FurnitureModel> findFurnitureById(UUID id) {
        return furnitureRepository.findById(id);
    }

    @Override
    public Optional<FurnitureModel> findFurnitureByName(String name) {
        return furnitureRepository.findByName(name);
    }

    @Override
    public FurnitureModel addFurniture(FurnitureModel furniture) {
        return furnitureRepository.save(furniture);
    }

    @Override
    public FurnitureModel updateFurniture(FurnitureModel furniture) {
        if (furniture != null && furniture.getId() != null &&
                furnitureRepository.existsById(furniture.getId())) {
            return furnitureRepository.save(furniture);
        }
        return null;
    }

    @Override
    public void deleteFurniture(UUID id) {
        furnitureRepository.deleteById(id);
    }
}