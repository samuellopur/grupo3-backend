package com.ecommerce.rutamtb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_OrderDetail;
    private int quantity;
    private Double unitaryPrice;
    private Double subTotalPrice;
    private Double TotalPrice;

//    Relacion OrderDetail to Order
//    Lado propietario
    @OneToOne
    @JoinColumn(name = "id_order")
    @JsonBackReference
    private Order order;

//    Relacion OrderDetail to payment
//    Lado propietario
    @OneToOne
    @JoinColumn (name = "id_payment")
    @JsonBackReference
    private Payment payment;

//    Relacion OrderDetail or Product
//    Lado propietario
    @OneToMany(mappedBy = "orderDetail")
    @JsonManagedReference
    private List<Product> productslist = new ArrayList<>();

//    Relacion Product to Imag
//    Lado propietario
    @OneToOne
    @JoinColumn(name = "id_imag")
    @JsonBackReference
    private Imag imag;
}
