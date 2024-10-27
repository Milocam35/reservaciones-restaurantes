package com.reservationApi.reservationCrud.controllers;

import com.reservationApi.reservationCrud.controllers.dto.LoginDTO;
import com.reservationApi.reservationCrud.controllers.dto.UserDTO;
import com.reservationApi.reservationCrud.models.UserModel;
import com.reservationApi.reservationCrud.responses.LoginMessage;
import com.reservationApi.reservationCrud.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * RestController: Esta anotación indica que la clase es un controlador en un API REST.
 * Combina las funcionalidades de @Controller (que indica que la clase maneja peticiones HTTP) y
 * @ResponseBody (que convierte las respuestas en JSON o XML automáticamente).
*/
@RestController

/**
 * RequestMapping: Esta anotación se utiliza para definir la ruta base del controlador.
 * Todos los endpoints (métodos) dentro del controlador tendrán esta ruta como prefijo.
 */
@RequestMapping("api/user")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getUsers(){ //El uso de <?> indica que la respuesta puede ser de cualquier tipo. En este caso, devolverá una lista de UserDTO.
        List<UserDTO> userDTOList = userService.getUsers()
                    .stream() //stream() convierte la lista de usuarios en un stream para poder aplicar operaciones funcionales, como map
                    .map(this::buildUserDTO) //map() es una operación intermedia que transforma cada elemento del stream. Cada objeto de UserModel será transformado a un UserDTO mediante el método buildUserDTO.
                    .toList(); // transforma el stream nuevamente a un List
        return ResponseEntity.ok(userDTOList);
    }

    /**
     * Esta funcion recibe una peticion HTTP GET y busca el respectivo usuario por el id
     * Importante recalcar que se utiliza un DTO (Data transfer object) para no tener que retornar una entidad
     * ya que eso es una mala practica.
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        Optional<UserModel> userOptional = userService.getUserById(id);
        return createUserResponseEntity(userOptional);
    }

    /**
     * @RequestBody: Esta anotación le dice a Spring que los datos del cuerpo de la solicitud
     * (en formato JSON o XML) se deben convertir automáticamente a un objeto Java del tipo UserModel.
     */

    @PostMapping(path="save")
    public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO) throws URISyntaxException {
        if(userDTO.getName().isBlank()){
            return ResponseEntity.badRequest().build();
        }
        userService.saveUser(buildUser(userDTO));
        return ResponseEntity.created(new URI("api/user/save")).build();
    }

    @PostMapping(path="/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO){
        if(loginDTO.getEmail().isBlank() || loginDTO.getPassword().isBlank()){
            return ResponseEntity.badRequest().build();
        }
        LoginMessage loginResponse = userService.loginUser(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<?> updateUserById(@RequestBody UserDTO request, @PathVariable("id") Long id){
        Optional<UserModel> userOptional = userService.getUserById(id);
        if(userOptional.isPresent()){
            UserModel userToUpdate = userOptional.get();
            userService.saveUser(setUserUpdateValues(request, userToUpdate));
            return ResponseEntity.ok("Usuario con id " + id + " actualizado exitosamente.");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") Long id){
        if(id != null){
            userService.deleteUserById(id);
            return ResponseEntity.ok("Usuario con id " + id + " eliminado existosamente.");
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> createUserResponseEntity(Optional<UserModel> userOptional){
        if(userOptional.isPresent()){
            UserModel user = userOptional.get();
            return ResponseEntity.ok(buildUserDTO(user));
        }
        return ResponseEntity.notFound().build();
    }

    public UserModel buildUser(UserDTO userDTO){
        return UserModel.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .cellphone(userDTO.getCellphone())
                .password(userDTO.getPassword())
                .role(userDTO.getRole())
                .build();
    }

    public UserDTO buildUserDTO(UserModel user){
        return UserDTO.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .cellphone(user.getCellphone())
                .password(user.getPassword())
                .role(user.getRole())
                .reservationList(user.getReservationList())
                .build();
    }

    public UserModel setUserUpdateValues(UserDTO userDTO, UserModel userToUpdate){
        userToUpdate.setName(userDTO.getName());
        userToUpdate.setCellphone(userDTO.getCellphone());
        userToUpdate.setEmail(userDTO.getEmail());
        userToUpdate.setPassword(userDTO.getPassword());
        userToUpdate.setRole(userDTO.getRole());
        return userToUpdate;
    }
}
