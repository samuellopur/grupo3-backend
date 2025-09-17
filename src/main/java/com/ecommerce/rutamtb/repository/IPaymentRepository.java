package com.ecommerce.rutamtb.repository;

import com.ecommerce.rutamtb.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaymentRepository extends JpaRepository<Payment, Long> {
}
