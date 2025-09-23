package com.ecommerce.rutamtb.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    
    private Long id_Payment;
    
    @NotBlank(message = "Payment method is required")
    @Pattern(regexp = "^(CREDIT_CARD|DEBIT_CARD|PAYPAL|BANK_TRANSFER|CASH)$", 
             message = "Payment method must be CREDIT_CARD, DEBIT_CARD, PAYPAL, BANK_TRANSFER, or CASH")
    private String method;
    
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    @Digits(integer = 8, fraction = 2, message = "Amount must have at most 8 integer digits and 2 decimal places")
    private BigDecimal amount;
    
    @NotNull(message = "Date is required")
    private Date date;
    
    @NotNull(message = "Status is required")
    private Boolean status;
    
    @NotNull(message = "Order ID is required")
    private Long orderId;
}

// DTO for payment creation
@Data
@NoArgsConstructor
@AllArgsConstructor
class PaymentCreateDto {
    
    @NotBlank(message = "Payment method is required")
    @Pattern(regexp = "^(CREDIT_CARD|DEBIT_CARD|PAYPAL|BANK_TRANSFER|CASH)$", 
             message = "Payment method must be CREDIT_CARD, DEBIT_CARD, PAYPAL, BANK_TRANSFER, or CASH")
    private String method;
    
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    @Digits(integer = 8, fraction = 2, message = "Amount must have at most 8 integer digits and 2 decimal places")
    private BigDecimal amount;
    
    @NotNull(message = "Order ID is required")
    private Long orderId;
}

// DTO for payment update
@Data
@NoArgsConstructor
@AllArgsConstructor
class PaymentUpdateDto {
    
    @Pattern(regexp = "^(CREDIT_CARD|DEBIT_CARD|PAYPAL|BANK_TRANSFER|CASH)$", 
             message = "Payment method must be CREDIT_CARD, DEBIT_CARD, PAYPAL, BANK_TRANSFER, or CASH")
    private String method;
    
    private Boolean status;
}