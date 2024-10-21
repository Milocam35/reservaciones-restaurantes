package com.reservationApi.reservationCrud.persistence;

import com.reservationApi.reservationCrud.models.ReservationModel;

import java.util.List;
import java.util.Optional;

public interface IReservationDAO {
    List<ReservationModel> getReservations();

    Optional<ReservationModel> getReservationById(Long id);

    ReservationModel saveReservation(ReservationModel reservation);

    ReservationModel updateReservationById(ReservationModel request, Long id);

    Boolean deleteReservationById(Long id);
}
