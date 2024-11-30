package com.web_project.furniture.service;

import com.web_project.furniture.model.CustomerModel;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    List<CustomerModel> findAllCustomers();
    Optional<CustomerModel> findCustomerById(UUID id);
    Optional<CustomerModel> findCustomerByName(String name);
    CustomerModel addCustomer(CustomerModel customer);
    CustomerModel updateCustomer(CustomerModel customer);
    void deleteCustomer(UUID id);
}