package com.ecommerce.rutamtb.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    
    private Long id_Order;
    
    @NotNull(message = "Date is required")
    private Date date;
    
    @NotBlank(message = "Status is required")
    @Pattern(regexp = "^(PENDING|PROCESSING|SHIPPED|DELIVERED|CANCELLED)$", 
             message = "Status must be PENDING, PROCESSING, SHIPPED, DELIVERED, or CANCELLED")
    private String status;
    
    @NotNull(message = "Subtotal price is required")
    @DecimalMin(value = "0.01", message = "Subtotal price must be greater than 0")
    @Digits(integer = 8, fraction = 2, message = "Subtotal price must have at most 8 integer digits and 2 decimal places")
    private BigDecimal subTotalPrice;
    
    @NotNull(message = "User ID is required")
    private Long userId;
    
    private String userName;
    private List<OrderDetailDto> orderDetails;
}

// DTO for order creation
@Data
@NoArgsConstructor
@AllArgsConstructor
class OrderCreateDto {
    
    @NotNull(message = "User ID is required")
    private Long userId;
    
    @NotEmpty(message = "Order details are required")
    private List<OrderDetailCreateDto> orderDetails;
}

// DTO for order update
@Data
@NoArgsConstructor
@AllArgsConstructor
class OrderUpdateDto {
    
    @Pattern(regexp = "^(PENDING|PROCESSING|SHIPPED|DELIVERED|CANCELLED)$", 
             message = "Status must be PENDING, PROCESSING, SHIPPED, DELIVERED, or CANCELLED")
    private String status;
}