package com.reservationApi.reservationCrud.controllers;

import com.reservationApi.reservationCrud.controllers.dto.RestaurantDTO;
import com.reservationApi.reservationCrud.models.RestaurantModel;
import com.reservationApi.reservationCrud.services.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/restaurant")

public class RestaurantController {
    private final IRestaurantService restaurantService;

    @Autowired
    public RestaurantController(IRestaurantService restaurantService){
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public ResponseEntity<?> getRestaurants(){
        List<RestaurantDTO> restaurantDTOList = restaurantService.getRestaurants()
                .stream()
                .map(this::buildRestaurantDTO)
                .toList();
        return ResponseEntity.ok(restaurantDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRestaurantById(@PathVariable Long id){
        Optional<RestaurantModel> restaurantOptional = restaurantService.getRestaurantById(id);
        return createRestaurantResponseEntity(restaurantOptional);
    }

    @GetMapping("/reservation/{id}")
    public ResponseEntity<?> getRestaurantByReservationId(Long id){
        Optional<RestaurantModel> restaurantOptional = restaurantService.getRestaurantByReservation(id);
        return createRestaurantResponseEntity(restaurantOptional);
    }

    @PostMapping(path="/save")
    public ResponseEntity<?> saveRestaurant(@RequestBody RestaurantDTO restaurantDTO) throws URISyntaxException {
        if(restaurantDTO.getName().isBlank()){
            return ResponseEntity.badRequest().build();
        }
        restaurantService.saveRestaurant(buildRestaurant(restaurantDTO));
        return ResponseEntity.created(new URI("api/restaurant/save")).build();
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<?> updateRestaurantById(@RequestBody RestaurantDTO request, @PathVariable("id") Long id){
        Optional<RestaurantModel> restaurantOptional = restaurantService.getRestaurantById(id);
        if(restaurantOptional.isPresent()){
            RestaurantModel restaurantToUpdate = restaurantOptional.get();
            restaurantService.saveRestaurant(setRestaurantUpdateValues(request, restaurantToUpdate));
            return ResponseEntity.ok("Restaurante con id " + id + " actualizado exitosamente.");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity<?> deleteRestaurantById(@PathVariable Long id){
        if(id==null){
            restaurantService.deleteRestaurantById(id);
            return ResponseEntity.ok("Mesa con id " + id + " eliminada exitosamente");
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<?> createRestaurantResponseEntity(Optional<RestaurantModel> restaurantOptional){
        if(restaurantOptional.isPresent()){
            RestaurantModel restaurant = restaurantOptional.get();
            return ResponseEntity.ok(buildRestaurantDTO(restaurant));
        }
        return ResponseEntity.notFound().build();
    }

    public RestaurantModel buildRestaurant(RestaurantDTO restaurantDTO){
        return RestaurantModel.builder()
                .restaurantId(restaurantDTO.getRestaurantId())
                .name(restaurantDTO.getName())
                .address(restaurantDTO.getAddress())
                .phone(restaurantDTO.getPhone())
                .openingHour(restaurantDTO.getOpeningHour())
                .closingHour(restaurantDTO.getClosingHour())
                .reservationList(restaurantDTO.getReservationList())
                .tableList(restaurantDTO.getTableList())
                .build();
    }

    public RestaurantDTO buildRestaurantDTO(RestaurantModel restaurant){
        return RestaurantDTO.builder()
                .restaurantId(restaurant.getRestaurantId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .phone(restaurant.getPhone())
                .openingHour(restaurant.getOpeningHour())
                .closingHour(restaurant.getClosingHour())
                .reservationList(restaurant.getReservationList())
                .tableList(restaurant.getTableList())
                .build();
    }

    public RestaurantModel setRestaurantUpdateValues(RestaurantDTO restaurantDTO, RestaurantModel restaurantToUpdate){
        restaurantToUpdate.setName(restaurantDTO.getName());
        restaurantToUpdate.setAddress(restaurantDTO.getAddress());
        restaurantToUpdate.setPhone(restaurantDTO.getPhone());
        restaurantToUpdate.setOpeningHour(restaurantDTO.getOpeningHour());
        restaurantToUpdate.setClosingHour(restaurantDTO.getClosingHour());
        return restaurantToUpdate;
    }
}
