package com.reservationApi.reservationCrud.services;

import com.reservationApi.reservationCrud.models.ReservationModel;

import java.util.List;
import java.util.Optional;

public interface IReservationService {
    List<ReservationModel> getReservations();

    Optional<ReservationModel> getReservationById(Long id);

    List<ReservationModel> getUserReservations(Long id);

    void saveReservation(ReservationModel reservation);

    void deleteReservationById(Long id);
}
