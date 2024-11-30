package com.web_project.furniture.service;

import com.web_project.furniture.model.DeliveryPersonModel;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeliveryPersonService {
    List<DeliveryPersonModel> findAllDeliveryPersons();
    Optional<DeliveryPersonModel> findDeliveryPersonById(UUID id);
    Optional<DeliveryPersonModel> findDeliveryPersonByName(String name);
    DeliveryPersonModel addDeliveryPerson(DeliveryPersonModel deliveryPerson);
    DeliveryPersonModel updateDeliveryPerson(DeliveryPersonModel deliveryPerson);
    void deleteDeliveryPerson(UUID id);
}