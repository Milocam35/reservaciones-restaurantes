package com.reservationApi.reservationCrud.repositories;

import com.reservationApi.reservationCrud.models.TableModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface ITableRepository extends JpaRepository<TableModel, Long> {
    @Query(value = "SELECT * FROM `Table` WHERE tableId = :id", nativeQuery = true)
    Optional<TableModel> findTableByIdNative(@Param("id") Long id);
    Optional<TableModel> findTableByReservationList_ReservationId(Long reservationId);

    List<TableModel> findByRestaurant_RestaurantId(Long restaurantId);
}
