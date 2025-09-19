package com.ecommerce.rutamtb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Order;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Double subTotalPrice;

//    Relacion Order to User
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    @JsonBackReference //se coloca en el lado hijo (el que tiene la clave foránea).
    private User user;


//    Relacion Order or OrderDetail
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<OrderDetail> orderDetails = new ArrayList<>();
}
