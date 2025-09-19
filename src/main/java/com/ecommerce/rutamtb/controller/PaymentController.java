package com.ecommerce.rutamtb.controller;

import com.ecommerce.rutamtb.model.Payment;
import com.ecommerce.rutamtb.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // Obtiene todos los pagos
    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        try {
            List<Payment> payments = paymentService.getAllPayments();
            return ResponseEntity.ok(payments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtiene un pago por ID
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        try {
            Payment payment = paymentService.getPaymenteById(id);
            if (payment != null) {
                return ResponseEntity.ok(payment);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Crea un nuevo pago
    @PostMapping
    public ResponseEntity<String> createPayment(@RequestBody Payment payment) {
        try {
            Payment savedPayment = paymentService.savePaymente(payment);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Payment created successfully with ID: " + savedPayment.getId_Payment());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error creating payment: " + e.getMessage());
        }
    }

    // Actualiza un pago existente
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePayment(@PathVariable Long id, @RequestBody Payment payment) {
        try {
            Payment existingPayment = paymentService.getPaymenteById(id);
            if (existingPayment != null) {
                payment.setId_Payment(id); // Asegurar que el ID coincida
                paymentService.savePaymente(payment);
                return ResponseEntity.ok("Payment updated successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error updating payment: " + e.getMessage());
        }
    }

    // Elimina un pago por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable Long id) {
        try {
            Payment payment = paymentService.getPaymenteById(id);
            if (payment != null) {
                paymentService.deletePayment(id);
                return ResponseEntity.ok("Payment deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Payment not found with ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting payment: " + e.getMessage());
        }
    }

    // Endpoints adicionales específicos para pagos (comentados)

    // Buscar pagos por usuario
    /*
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Payment>> getPaymentsByUser(@PathVariable Long userId) {
        try {
            List<Payment> payments = paymentService.findByUserId(userId);
            return ResponseEntity.ok(payments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    */

    // Buscar pagos por estado
    /*
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Payment>> getPaymentsByStatus(@PathVariable String status) {
        try {
            List<Payment> payments = paymentService.findByStatus(status);
            return ResponseEntity.ok(payments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    */

    // Buscar pagos por método de pago
    /*
    @GetMapping("/method/{paymentMethod}")
    public ResponseEntity<List<Payment>> getPaymentsByMethod(@PathVariable String paymentMethod) {
        try {
            List<Payment> payments = paymentService.findByPaymentMethod(paymentMethod);
            return ResponseEntity.ok(payments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    */

    // Buscar pagos por rango de fechas
    /*
    @GetMapping("/date-range")
    public ResponseEntity<List<Payment>> getPaymentsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        try {
            List<Payment> payments = paymentService.findByDateRange(startDate, endDate);
            return ResponseEntity.ok(payments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    */

    // Procesar un pago (cambiar estado)
    /*
    @PutMapping("/{id}/process")
    public ResponseEntity<String> processPayment(@PathVariable Long id) {
        try {
            Payment payment = paymentService.getPaymenteById(id);
            if (payment != null) {
                paymentService.processPayment(id);
                return ResponseEntity.ok("Payment processed successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error processing payment: " + e.getMessage());
        }
    }
    */
}