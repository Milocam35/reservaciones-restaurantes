package com.reservationApi.reservationCrud.persistence;

import com.reservationApi.reservationCrud.models.UserModel;

import java.util.List;
import java.util.Optional;

public interface IUserDAO {
    List<UserModel> getUsers();

    Optional<UserModel> getUserById(Long id);

    Optional<UserModel> getUserByEmail(String email);

    void saveUser(UserModel user);

    void deleteUserById(Long id);
}
