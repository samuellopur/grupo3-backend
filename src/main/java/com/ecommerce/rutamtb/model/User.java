package com.ecommerce.rutamtb.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id_User;
    private String name;
    private String lastName;
    private String email;
    private String address;
    private String phone;
    private Date registerDate;

//    Relacion User to Order
//    Lado inverso
    /* mappedBy = "user", parte inversa de una relación bidireccional, indica que la entidad Order implementa user,
    el FK esta definida en Order, por tanto Order es el propietario
    * cascade = CascadeType.ALL, indica que todas las operaciones sobre User se propagan a sus Order (PERSIST, MERGE, REMOVE, REFRESH, DETACH).
    * Cuidado con CascadeType.ALL: evalúa si realmente necesitas REMOVE y otros. A veces cascade = {PERSIST, MERGE} es suficiente.
    * Ejemplo: si borras un User, JPA también borrará sus Order (por REMOVE incluido en ALL)
    * No mezcles mappedBy con @JoinColumn en el mismo lado de la relación.
    * Bidireccional: @OneToMany(mappedBy = "user") en User y @ManyToOne @JoinColumn(...) en Order. (Recomendado)
    * Unidireccional desde User: @OneToMany sin mappedBy, y sí @JoinColumn en User (entonces User es el propietario y
      JPA crea la FK en la tabla Order).*/

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE})

    /*@JsonManagedReference: Anotación de Jackson para evitar recursión infinita en serialización JSON en relaciones bidireccionales.
    * @JsonManagedReference va en el lado “padre” (el que quieres incluir).
    El lado inverso (Order.user) debe llevar @JsonBackReference. Efecto: al serializar User, Jackson incluirá la lista
    * orders pero omitirá la referencia Order.user para evitar bucles.*/
    @JsonManagedReference //se coloca en el lado padre (el que contiene la colección o lista).
    private List<Order> orderList = new ArrayList<>();

}
