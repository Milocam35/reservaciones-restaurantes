package com.reservationApi.reservationCrud.services.impl;

import com.reservationApi.reservationCrud.models.RestaurantModel;
import com.reservationApi.reservationCrud.persistence.IRestaurantDAO;
import com.reservationApi.reservationCrud.services.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class RestaurantServiceImpl implements IRestaurantService {

    private final IRestaurantDAO restaurantDAO;

    @Autowired
    public RestaurantServiceImpl(IRestaurantDAO restaurantDAO){ this.restaurantDAO = restaurantDAO;}

    @Override
    public List<RestaurantModel> getRestaurants() {
        return restaurantDAO.getRestaurants();
    }

    @Override
    public Optional<RestaurantModel> getRestaurantById(Long id) {
        return restaurantDAO.getRestaurantById(id);
    }

    @Override
    public Optional<RestaurantModel> getRestaurantByReservation(Long id) {
        return restaurantDAO.getRestaurantByReservation(id);
    }

    @Override
    public void saveRestaurant(RestaurantModel restaurant) {
        restaurantDAO.saveRestaurant(restaurant);
    }

    @Override
    public void deleteRestaurantById(Long id) {
        restaurantDAO.deleteRestaurantById(id);
    }


}
