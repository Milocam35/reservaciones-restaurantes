package com.reservationApi.reservationCrud.repositories;

import com.reservationApi.reservationCrud.models.ReservationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IReservationRepository extends JpaRepository<ReservationModel, Long> {
    @Query(value = "SELECT * FROM Reservation WHERE reservationId = :id", nativeQuery = true)
    Optional<ReservationModel> findReservationByIdNative(@Param("id") Long id);
    List<ReservationModel> findByUser_UserId(Long userId);
}
