package com.ecommerce.rutamtb.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Category;
    private String category_name;
    private String category_description;

//    Relación Category to Product

    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<Product> productList = new ArrayList<>();
}
