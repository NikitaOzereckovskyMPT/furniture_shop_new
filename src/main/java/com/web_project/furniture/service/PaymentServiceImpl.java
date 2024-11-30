package com.web_project.furniture.service;

import com.web_project.furniture.model.PaymentModel;
import com.web_project.furniture.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public List<PaymentModel> findAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Optional<PaymentModel> findPaymentById(UUID id) {
        return paymentRepository.findById(id);
    }

    @Override
    public PaymentModel addPayment(PaymentModel payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public PaymentModel updatePayment(PaymentModel payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public void deletePayment(UUID id) {
        paymentRepository.deleteById(id);
    }
}