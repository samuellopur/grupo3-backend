package com.ecommerce.rutamtb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "images")
public class Imag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Imag;

    @Column(nullable = false)
    private String nameImag;

    @Column(nullable = false)
    private String type;

    private Long size;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String alt;

    // Relacion Imag -> Product (One-to-One)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product")
    @JsonBackReference
    private Product product;
}