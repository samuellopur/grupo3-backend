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
    private String method;
    private Double amount;
    private Date date;
    private Boolean Status;

//    Relacion Payment or OrderDetail
//    Lado inverso
    @OneToOne(mappedBy = "payment", cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_order_detail")
    @JsonManagedReference
    private OrderDetail orderDetail;
}
