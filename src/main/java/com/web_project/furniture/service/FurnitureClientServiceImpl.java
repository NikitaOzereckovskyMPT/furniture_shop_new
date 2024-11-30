package com.web_project.furniture.service;

import com.web_project.furniture.model.FurnitureClientModel;
import com.web_project.furniture.model.UserModel;
import com.web_project.furniture.repository.FurnitureClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class FurnitureClientServiceImpl implements FurnitureClientService {

    private final FurnitureClientRepository furnitureClientRepository;

    @Autowired
    public FurnitureClientServiceImpl(FurnitureClientRepository furnitureClientRepository) {
        this.furnitureClientRepository = furnitureClientRepository;
    }

    @Override
    public List<FurnitureClientModel> findAllFurnitureClients() {
        return furnitureClientRepository.findAll();
    }

    @Override
    public Optional<FurnitureClientModel> findFurnitureClientById(UUID id) {
        return furnitureClientRepository.findById(id);
    }

    @Override
    public List<FurnitureClientModel> findFurnitureClientsByUser(UserModel user) {
        return furnitureClientRepository.findByUser(user);
    }

    @Override
    public List<FurnitureClientModel> findFurnitureClientsByOrderId(UUID orderId) {
        return furnitureClientRepository.findByOrderId(orderId);
    }

    @Override
    public FurnitureClientModel addFurnitureClient(FurnitureClientModel furnitureClient) {
        return furnitureClientRepository.save(furnitureClient);
    }

    @Override
    public FurnitureClientModel updateFurnitureClient(FurnitureClientModel furnitureClient) {
        if (furnitureClient.getId() != null &&
                furnitureClientRepository.existsById(furnitureClient.getId())) {
            return furnitureClientRepository.save(furnitureClient);
        }
        return null;
    }

    @Override
    public void deleteFurnitureClient(UUID id) {
        if (furnitureClientRepository.existsById(id)) {
            furnitureClientRepository.deleteById(id);
        }
    }
}