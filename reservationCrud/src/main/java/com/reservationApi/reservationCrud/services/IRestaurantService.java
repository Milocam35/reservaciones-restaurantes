package com.reservationApi.reservationCrud.services;

import com.reservationApi.reservationCrud.models.RestaurantModel;

import java.util.List;
import java.util.Optional;

public interface IRestaurantService {
    List<RestaurantModel> getRestaurants();

    Optional<RestaurantModel> getRestaurantById(Long id);

    Optional<RestaurantModel> getRestaurantByReservation(Long id);

    void saveRestaurant(RestaurantModel restaurant);

    void deleteRestaurantById(Long id);
}
