package com.ecommerce.rutamtb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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


}
