package com.ecommerce.rutamtb.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Data

@Entity
public class Imag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Imag;
    private String url;
    private String description;
    private String alt;

//    Relacion Product to Imag
//    Lado inverso

    @OneToOne(mappedBy = "imag")
    @JsonManagedReference
    private Product product;


}
