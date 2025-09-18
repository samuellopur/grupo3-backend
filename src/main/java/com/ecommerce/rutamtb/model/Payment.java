package com.ecommerce.rutamtb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Payment;

    @Column(nullable = false)
    private String method;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Boolean Status;

//    Relacion Payment or OrderDetail
//    Lado inverso
    @OneToOne(mappedBy = "payment", cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_order_detail")
    @JsonManagedReference
    private OrderDetail orderDetail;
}
