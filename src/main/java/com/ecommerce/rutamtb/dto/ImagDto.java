package com.ecommerce.rutamtb.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImagDto {
    
    private Long id_Imag;
    
    @NotBlank(message = "Image name is required")
    @Size(min = 1, max = 100, message = "Image name must be between 1 and 100 characters")
    private String nameImag;
    
    @NotBlank(message = "Image type is required")
    @Pattern(regexp = "^(image/jpeg|image/png|image/gif|image/webp)$", 
             message = "Image type must be jpeg, png, gif, or webp")
    private String type;
    
    @Min(value = 1, message = "Image size must be greater than 0")
    private Long size;
    
    @NotBlank(message = "Image URL is required")
    @Size(max = 500, message = "URL must not exceed 500 characters")
    private String url;
    
    @NotBlank(message = "Description is required")
    @Size(min = 5, max = 200, message = "Description must be between 5 and 200 characters")
    private String description;
    
    @NotBlank(message = "Alt text is required")
    @Size(min = 5, max = 100, message = "Alt text must be between 5 and 100 characters")
    private String alt;
    
    @NotNull(message = "Product ID is required")
    private Long productId;
    
    private String productName;
}

// DTO for image creation
@Data
@NoArgsConstructor
@AllArgsConstructor
class ImagCreateDto {
    
    @NotBlank(message = "Image name is required")
    @Size(min = 1, max = 100, message = "Image name must be between 1 and 100 characters")
    private String nameImag;
    
    @NotBlank(message = "Image type is required")
    @Pattern(regexp = "^(image/jpeg|image/png|image/gif|image/webp)$", 
             message = "Image type must be jpeg, png, gif, or webp")
    private String type;
    
    @Min(value = 1, message = "Image size must be greater than 0")
    private Long size;
    
    @NotBlank(message = "Image URL is required")
    @Size(max = 500, message = "URL must not exceed 500 characters")
    private String url;
    
    @NotBlank(message = "Description is required")
    @Size(min = 5, max = 200, message = "Description must be between 5 and 200 characters")
    private String description;
    
    @NotBlank(message = "Alt text is required")
    @Size(min = 5, max = 100, message = "Alt text must be between 5 and 100 characters")
    private String alt;
    
    @NotNull(message = "Product ID is required")
    private Long productId;
}

// DTO for image update
@Data
@NoArgsConstructor
@AllArgsConstructor
class ImagUpdateDto {
    
    @Size(min = 1, max = 100, message = "Image name must be between 1 and 100 characters")
    private String nameImag;
    
    @Pattern(regexp = "^(image/jpeg|image/png|image/gif|image/webp)$", 
             message = "Image type must be jpeg, png, gif, or webp")
    private String type;
    
    @Min(value = 1, message = "Image size must be greater than 0")
    private Long size;
    
    @Size(max = 500, message = "URL must not exceed 500 characters")
    private String url;
    
    @Size(min = 5, max = 200, message = "Description must be between 5 and 200 characters")
    private String description;
    
    @Size(min = 5, max = 100, message = "Alt text must be between 5 and 100 characters")
    private String alt;
}