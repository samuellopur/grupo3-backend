package com.ecommerce.rutamtb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"category", "imag", "orderDetails"})
@ToString(exclude = {"category", "imag", "orderDetails"})
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Product;

    @Column(nullable = false)
    private String product_name;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private int stock; //

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String sex;

    // Relacion Product -> Category
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_category")
    @JsonBackReference
    private Category category;

    // Relacion Product -> Imag (One-to-One)
    @OneToOne(mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Imag imag;

    //Relación inversa Product con OrderDetails
    @OneToMany(mappedBy = "product")
    @JsonIgnore // Evita loops infinitos
    private List<OrderDetail> orderDetails = new ArrayList<>();
}
