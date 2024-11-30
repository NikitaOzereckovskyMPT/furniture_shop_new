package com.web_project.furniture.repository;

import com.web_project.furniture.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<CustomerModel, UUID> {
    boolean existsByName(String name);
    Optional<CustomerModel> findByName(String name);
}