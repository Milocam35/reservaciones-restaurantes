package com.reservationApi.reservationCrud.repositories;

import com.reservationApi.reservationCrud.models.RestaurantModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface IRestaurantRepository extends JpaRepository<RestaurantModel, Long> {
    Optional<RestaurantModel> findByReservationList_ReservationId(Long reservationId);
}
