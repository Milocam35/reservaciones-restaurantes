package com.reservationApi.reservationCrud.services.impl;

import com.reservationApi.reservationCrud.controllers.dto.LoginDTO;
import com.reservationApi.reservationCrud.models.UserModel;
import com.reservationApi.reservationCrud.persistence.IUserDAO;
import com.reservationApi.reservationCrud.responses.LoginMessage;
import com.reservationApi.reservationCrud.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * La capa de servicio se encarga de la lógica de negocio de la aplicación.
 * En este caso, UserService interactúa con la base de datos a través del repositorio IUserRepository.
 */
@Service
public class UserServiceImpl implements IUserService {
/**
 * @Autowired le indica a Spring que inyecte automáticamente
 * una instancia de la interfaz IUserRepository en esta clase.
 *
 * Inyección de dependencias permite desacoplar el código, ya que no necesitamos crear
 * manualmente la instancia de IUserRepository. Spring se encarga de hacerlo por nosotros.
 */

    private final IUserDAO userDAO;

    @Autowired
    public UserServiceImpl(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<UserModel> getUsers() {
        return userDAO.getUsers();
    }

    @Override
    public Optional<UserModel> getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public Optional<UserModel> getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    @Override
    public LoginMessage loginUser(LoginDTO loginDTO) {
        String msg = "";
        Optional<UserModel> data_user = userDAO.getUserByEmail(loginDTO.getEmail());
        if (data_user.isPresent()) {
            String password = loginDTO.getPassword();
            String encodedPassword = data_user.get().getPassword();
            Boolean isPwdRight = password.equals(encodedPassword);
            if (isPwdRight) {
                Optional<UserModel> user = userDAO.getUserByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (user.isPresent()) {
                    return new LoginMessage("Login Success", true);
                } else {
                    return new LoginMessage("Login Failed", false);
                }
            } else {
                return new LoginMessage("password Not Match", false);
            }
        }else {
            return new LoginMessage("Email not exits", false);
        }
    }

    @Override
    public void saveUser(UserModel user) {
        userDAO.saveUser(user);
    }


    @Override
    public void deleteUserById(Long id) {
        userDAO.deleteUserById(id);
    }
}
