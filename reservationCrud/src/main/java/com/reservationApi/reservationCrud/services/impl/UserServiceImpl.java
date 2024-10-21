package com.reservationApi.reservationCrud.services.impl;

import com.reservationApi.reservationCrud.models.UserModel;
import com.reservationApi.reservationCrud.persistence.IUserDAO;
import com.reservationApi.reservationCrud.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;

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
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserServiceImpl(IUserDAO userDAO, JdbcTemplate jdbcTemplate) {
        this.userDAO = userDAO;
        this.jdbcTemplate = jdbcTemplate;
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
    public void saveUser(UserModel user) {
        userDAO.saveUser(user);
    }


    @Override
    public void deleteUserById(Long id) {
        userDAO.deleteUserById(id);
    }
}
