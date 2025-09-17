package com.ecommerce.rutamtb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Order;
    private Date date;
    private String status;

//    Relacion Order to User
//    Lado propietario aquí va la FK "id_user"
    /* fetch = FetchType.LAZY en colecciones (OneToMany es LAZY por defecto) y en ManyToOne explícitamente LAZY si
    quieres evitar cargas inesperadas (por defecto ManyToOne puede ser EAGER)*/
    @ManyToOne

    /*@JoinColumn(name = "id_user") Define la columna de clave foránea (FK) en la tabla de la entidad propietaria
    referencedColumnName: solo hace falta si apuntas a una columna que no sea la PK de la entidad, Si apuntas a la
    PK, lo normal es omitir referencedColumnName*/
    @JoinColumn(name = "id_user")
    @JsonBackReference //se coloca en el lado hijo (el que tiene la clave foránea).
    private User user;


//    Relacion Order or OrderDetail
//    Lado inverso
    @OneToOne (mappedBy = "orderdetail")
    @JsonManagedReference
    private OrderDetail orderDetail;
}
