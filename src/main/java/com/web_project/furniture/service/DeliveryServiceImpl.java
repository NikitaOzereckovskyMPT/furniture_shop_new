package com.web_project.furniture.service;

import com.web_project.furniture.model.DeliveryModel;
import com.web_project.furniture.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    public List<DeliveryModel> findAllDeliveries() {
        return deliveryRepository.findAll();
    }

    @Override
    public Optional<DeliveryModel> findDeliveryById(UUID id) {
        return deliveryRepository.findById(id);
    }

    @Override
    public Optional<DeliveryModel> findDeliveryByStatus(String status) {
        return deliveryRepository.findByStatus(status);
    }

    @Override
    public DeliveryModel addDelivery(DeliveryModel delivery) {
        return deliveryRepository.save(delivery);
    }

    @Override
    public DeliveryModel updateDelivery(DeliveryModel delivery) {
        return deliveryRepository.save(delivery);
    }

    @Override
    public void deleteDelivery(UUID id) {
        deliveryRepository.deleteById(id);
    }
}