package com.reservationApi.reservationCrud.persistence;

import com.reservationApi.reservationCrud.models.UserModel;
import org.apache.catalina.User;

import java.util.List;
import java.util.Optional;

public interface IUserDAO {
    List<UserModel> getUsers();

    Optional<UserModel> getUserById(Long id);

    Optional<UserModel> getUserByEmail(String email);

    Optional<UserModel> getUserByEmailAndPassword(String email, String password);

    void saveUser(UserModel user);

    void deleteUserById(Long id);
}
