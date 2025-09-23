package com.ecommerce.rutamtb.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    
    private Long id_Product;
    
    @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String product_name;
    
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    @Digits(integer = 8, fraction = 2, message = "Price must have at most 8 integer digits and 2 decimal places")
    private BigDecimal price;
    
    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock cannot be negative")
    private Integer stock;
    
    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    private String description;
    
    @NotBlank(message = "Sex category is required")
    @Pattern(regexp = "^(Male|Female|Unisex)$", message = "Sex must be Male, Female, or Unisex")
    private String sex;
    
    @NotNull(message = "Category ID is required")
    private Long categoryId;
    
    private String categoryName;
}

// DTO for product creation
@Data
@NoArgsConstructor
@AllArgsConstructor
class ProductCreateDto {
    
    @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String product_name;
    
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    @Digits(integer = 8, fraction = 2, message = "Price must have at most 8 integer digits and 2 decimal places")
    private BigDecimal price;
    
    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock cannot be negative")
    private Integer stock;
    
    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    private String description;
    
    @NotBlank(message = "Sex category is required")
    @Pattern(regexp = "^(Male|Female|Unisex)$", message = "Sex must be Male, Female, or Unisex")
    private String sex;
    
    @NotNull(message = "Category ID is required")
    private Long categoryId;
}

// DTO for product update
@Data
@NoArgsConstructor
@AllArgsConstructor
class ProductUpdateDto {
    
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String product_name;
    
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    @Digits(integer = 8, fraction = 2, message = "Price must have at most 8 integer digits and 2 decimal places")
    private BigDecimal price;
    
    @Min(value = 0, message = "Stock cannot be negative")
    private Integer stock;
    
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    private String description;
    
    @Pattern(regexp = "^(Male|Female|Unisex)$", message = "Sex must be Male, Female, or Unisex")
    private String sex;
    
    private Long categoryId;
}