package com.ecommerce.rutamtb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;


@Data

@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_OrderDetail;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private Double unitaryPrice;

    @Column(nullable = false)
    private Double subTotalPrice;

//    Relacion OrderDetail to Order
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order", nullable = false)
    @JsonBackReference
    private Order order;

//    Relacion OrderDetail or Product
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;
}
