package com.web_project.furniture.service;

import com.web_project.furniture.model.FurnitureClientModel;
import com.web_project.furniture.model.UserModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FurnitureClientService {
    List<FurnitureClientModel> findAllFurnitureClients();
    Optional<FurnitureClientModel> findFurnitureClientById(UUID id);
    List<FurnitureClientModel> findFurnitureClientsByUser(UserModel user);
    List<FurnitureClientModel> findFurnitureClientsByOrderId(UUID orderId);
    FurnitureClientModel addFurnitureClient(FurnitureClientModel furnitureClient);
    FurnitureClientModel updateFurnitureClient(FurnitureClientModel furnitureClient);
    void deleteFurnitureClient(UUID id);
}