package com.ecommerce.rutamtb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Product;

    @Column(nullable = false)
    private String product_name;

    @Column(nullable = false)
    private String price;

    @Column(nullable = false)
    private int Stock;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
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
