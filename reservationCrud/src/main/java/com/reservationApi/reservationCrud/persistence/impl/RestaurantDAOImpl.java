package com.reservationApi.reservationCrud.persistence.impl;

import com.reservationApi.reservationCrud.models.RestaurantModel;
import com.reservationApi.reservationCrud.persistence.IRestaurantDAO;
import com.reservationApi.reservationCrud.repositories.IRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component

public class RestaurantDAOImpl implements IRestaurantDAO {

    private final IRestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantDAOImpl(IRestaurantRepository restaurantRepository){ this.restaurantRepository = restaurantRepository;}

    @Override
    public List<RestaurantModel> getRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Optional<RestaurantModel> getRestaurantById(Long id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public Optional<RestaurantModel> getRestaurantByReservation(Long id) {
        return restaurantRepository.findByReservationList_ReservationId(id);
    }

    @Override
    public void saveRestaurant(RestaurantModel table) {
        restaurantRepository.save(table);
    }

    @Override
    public void deleteRestaurantById(Long id) {
        restaurantRepository.deleteById(id);
    }

}
