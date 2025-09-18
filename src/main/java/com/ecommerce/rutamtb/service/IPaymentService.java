package com.ecommerce.rutamtb.service;

import com.ecommerce.rutamtb.model.Payment;
import java.util.List;

public interface IPaymentService {
    List<Payment> getAllPayments();
    Payment getPaymenteById(Long id);
    Payment savePaymente(Payment payment);
    void deletePayment(Long id);
}
