package com.reservationApi.reservationCrud.persistence.impl;

import com.reservationApi.reservationCrud.models.UserModel;
import com.reservationApi.reservationCrud.persistence.IUserDAO;
import com.reservationApi.reservationCrud.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDAOImpl implements IUserDAO {

    private final IUserRepository userRepository;

    @Autowired
    public UserDAOImpl(IUserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<UserModel> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserModel> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<UserModel> getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public void saveUser(UserModel user) {
        userRepository.save(user);
    }


    @Override
    public void deleteUserById(Long id) { userRepository.deleteById(id);}
}
