package com.web_project.furniture.service;

import com.web_project.furniture.model.DeliveryModel;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeliveryService {
    List<DeliveryModel> findAllDeliveries();
    Optional<DeliveryModel> findDeliveryById(UUID id);
    Optional<DeliveryModel> findDeliveryByStatus(String status);
    DeliveryModel addDelivery(DeliveryModel delivery);
    DeliveryModel updateDelivery(DeliveryModel delivery);
    void deleteDelivery(UUID id);
}