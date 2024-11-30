package com.web_project.furniture.repository;

import com.web_project.furniture.model.PaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<PaymentModel, UUID> {
    boolean existsByPaymentMethod(String paymentMethod);
    Optional<PaymentModel> findByPaymentMethod(String paymentMethod);
}
