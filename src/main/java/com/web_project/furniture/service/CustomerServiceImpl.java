package com.web_project.furniture.service;

import com.web_project.furniture.model.CustomerModel;
import com.web_project.furniture.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerModel> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<CustomerModel> findCustomerById(UUID id) {
        return customerRepository.findById(id);
    }

    @Override
    public Optional<CustomerModel> findCustomerByName(String name) {
        return customerRepository.findByName(name);
    }

    @Override
    public CustomerModel addCustomer(CustomerModel customer) {
        return customerRepository.save(customer);
    }

    @Override
    public CustomerModel updateCustomer(CustomerModel customer) {
        if(customerRepository.existsById(customer.getId())){
            return customerRepository.save(customer);
        }
        return null;
    }

    @Override
    public void deleteCustomer(UUID id) {
        if(customerRepository.existsById(id)){
            customerRepository.deleteById(id);
        }
    }
}
