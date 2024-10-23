package com.reservationApi.reservationCrud.repositories;

import com.reservationApi.reservationCrud.models.TableModel;
import com.reservationApi.reservationCrud.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface ITableRepository extends JpaRepository<TableModel, Long> {
    //vamos a agregar una función para buscar todas las mesas de un restarante especifico
    Optional<TableModel> findTableByReservation_ReservationId(Long reservationId);
}
