package com.ecommerce.rutamtb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data

@Entity
public class Category {
    @Id
    private Long id_Category;
    private String category_name;
    private String category_description;

}
