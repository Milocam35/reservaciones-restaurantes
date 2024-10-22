 package com.reservationApi.reservationCrud.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


/**
 * Entity Marca la clase UserModel como una entidad gestionada por JPA/Hibernate.
 * Cada instancia de esta clase representará una fila en la tabla User en la base de datos.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

/**
 * Table Define el nombre de la tabla en la base de datos que corresponde a esta entidad.
 * Si no se usa, JPA asume que la tabla tiene el mismo nombre que la clase (en este caso, UserModel).
 */
@Table(name="User")
public class UserModel {
    //@Id Indica que el campo id es la clave primaria de la entidad.

    /**
     * GeneratedValue Define que el valor del campo id se genera automáticamente por la base de datos (usando auto-incremento).
     * La base de datos incrementará automáticamente este valor cuando se inserten nuevas filas.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId", nullable = false)
    private Long userId;

    //Atributos principales asociados con la entidad de la base de datos.
    @Column
    private String name;

    @Column
    private String cellphone;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    //Lista de reservas del cliente, ya que un cliente puedo tener muchas reservas
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private List<ReservationModel> reservationList = new ArrayList<>();
}
