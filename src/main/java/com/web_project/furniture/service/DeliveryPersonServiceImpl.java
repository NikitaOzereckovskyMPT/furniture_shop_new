package com.web_project.furniture.service;

import com.web_project.furniture.model.DeliveryPersonModel;
import com.web_project.furniture.repository.DeliveryPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class DeliveryPersonServiceImpl implements DeliveryPersonService {

    private final DeliveryPersonRepository deliveryPersonRepository;

    @Autowired
    public DeliveryPersonServiceImpl(DeliveryPersonRepository deliveryPersonRepository) {
        this.deliveryPersonRepository = deliveryPersonRepository;
    }

    @Override
    public List<DeliveryPersonModel> findAllDeliveryPersons() {
        return deliveryPersonRepository.findAll();
    }

    @Override
    public Optional<DeliveryPersonModel> findDeliveryPersonById(UUID id) {
        return deliveryPersonRepository.findById(id);
    }

    @Override
    public Optional<DeliveryPersonModel> findDeliveryPersonByName(String name) {
        return deliveryPersonRepository.findByName(name);
    }

    @Override
    public DeliveryPersonModel addDeliveryPerson(DeliveryPersonModel deliveryPerson) {
        return deliveryPersonRepository.save(deliveryPerson);
    }

    @Override
    public DeliveryPersonModel updateDeliveryPerson(DeliveryPersonModel deliveryPerson) {
        if (deliveryPerson != null && deliveryPerson.getId() != null &&
                deliveryPersonRepository.existsById(deliveryPerson.getId())) {
            return deliveryPersonRepository.save(deliveryPerson);
        }
        return null;
    }

    @Override
    public void deleteDeliveryPerson(UUID id) {
        if (deliveryPersonRepository.existsById(id)) {
            deliveryPersonRepository.deleteById(id);
        }
    }
}