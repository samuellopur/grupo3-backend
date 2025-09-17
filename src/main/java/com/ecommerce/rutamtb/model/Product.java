package com.ecommerce.rutamtb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Product;
    private String product_name;
    private String price;
    private int Stock;
    private String description;
    private String sex;

//    Relacion Product to OrderDetail
//    Lado inverso
    @ManyToOne
    @JoinColumn(name = "id_order_detail")
    @JsonBackReference
    private OrderDetail orderDetail;

//    Relacion Product to Imag
//    Lado propietario
    @OneToOne
    @JoinColumn(name = "id_imag")
    @JsonBackReference
    private Imag imag;

//    Relacion Product to category

    @OneToOne
    @JoinColumn(name = "id_category")
    @JsonBackReference
    private Category category;
}
