package com.ecommerce.rutamtb.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    
    private Long id_Category;
    
    @NotBlank(message = "Category name is required")
    @Size(min = 2, max = 50, message = "Category name must be between 2 and 50 characters")
    private String category_name;
    
    private Integer productCount;
    private List<String> productNames;
}

// DTO for category creation
@Data
@NoArgsConstructor
@AllArgsConstructor
class CategoryCreateDto {
    
    @NotBlank(message = "Category name is required")
    @Size(min = 2, max = 50, message = "Category name must be between 2 and 50 characters")
    private String category_name;
}

// DTO for category update
@Data
@NoArgsConstructor
@AllArgsConstructor
class CategoryUpdateDto {
    
    @Size(min = 2, max = 50, message = "Category name must be between 2 and 50 characters")
    private String category_name;
}