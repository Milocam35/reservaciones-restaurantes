package com.reservationApi.reservationCrud.repositories;

import com.reservationApi.reservationCrud.models.ReservationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReservationRepository extends JpaRepository<ReservationModel, Long> {
}
