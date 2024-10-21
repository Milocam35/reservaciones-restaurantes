package com.reservationApi.reservationCrud.repositories;

import com.reservationApi.reservationCrud.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Un repository es una interfaz que permite hacer consultas a la base de datos
 * Extiende JpaRepository para poder utilizar las funcionalidades de Jpa R
 */
@Repository //Esta anotación indica que la interfaz es un componente de acceso a datos.

/**
 * La interfaz hereda de JpaRepository, que es una interfaz proporcionada por Spring Data JPA
 * que permite operaciones CRUD (Create, Read, Update, Delete) y más, sobre la entidad UserModel.
 */
public interface IUserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> getUserByEmail(String email);
}
