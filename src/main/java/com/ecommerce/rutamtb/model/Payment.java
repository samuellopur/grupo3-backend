package com.ecommerce.rutamtb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"order"})
@ToString(exclude = {"order"})
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Payment;

    @Column(nullable = false)
    private String method;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Boolean status; //

    // Relacion Payment -> Order (no OrderDetail)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order")
    @JsonBackReference
    private Order order;
}