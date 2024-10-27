package com.reservationApi.reservationCrud.services;

import com.reservationApi.reservationCrud.controllers.dto.LoginDTO;
import com.reservationApi.reservationCrud.models.UserModel;
import com.reservationApi.reservationCrud.responses.LoginMessage;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<UserModel> getUsers();

    Optional<UserModel> getUserById(Long id);

    Optional<UserModel> getUserByEmail(String email);

    LoginMessage loginUser(LoginDTO loginDTO);

    void saveUser(UserModel user);

    void deleteUserById(Long id);
}
