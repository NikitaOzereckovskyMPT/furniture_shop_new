package com.web_project.furniture.service;

import com.web_project.furniture.model.PaymentModel;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentService {
    List<PaymentModel> findAllPayments();
    Optional<PaymentModel> findPaymentById(UUID id);
    PaymentModel addPayment(PaymentModel payment);
    PaymentModel updatePayment(PaymentModel payment);
    void deletePayment(UUID id);
}
