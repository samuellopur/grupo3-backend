package com.ecommerce.rutamtb.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto {
    
    private Long id_OrderDetail;
    
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;
    
    @NotNull(message = "Unitary price is required")
    @DecimalMin(value = "0.01", message = "Unitary price must be greater than 0")
    @Digits(integer = 8, fraction = 2, message = "Unitary price must have at most 8 integer digits and 2 decimal places")
    private BigDecimal unitaryPrice;
    
    @NotNull(message = "Subtotal price is required")
    @DecimalMin(value = "0.01", message = "Subtotal price must be greater than 0")
    @Digits(integer = 8, fraction = 2, message = "Subtotal price must have at most 8 integer digits and 2 decimal places")
    private BigDecimal subTotalPrice;
    
    @NotNull(message = "Product ID is required")
    private Long productId;
    
    private String productName;
    private Long orderId;
}

// DTO for order detail creation
@Data
@NoArgsConstructor
@AllArgsConstructor
class OrderDetailCreateDto {
    
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;
    
    @NotNull(message = "Product ID is required")
    private Long productId;
}

// DTO for order detail update
@Data
@NoArgsConstructor
@AllArgsConstructor
class OrderDetailUpdateDto {
    
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;
}