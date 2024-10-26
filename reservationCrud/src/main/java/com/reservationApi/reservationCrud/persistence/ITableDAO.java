package com.reservationApi.reservationCrud.persistence;

import com.reservationApi.reservationCrud.models.TableModel;

import java.util.List;
import java.util.Optional;

public interface ITableDAO {
    List<TableModel> getTables();

    Optional<TableModel> getTableById(Long id);

    Optional<TableModel> getTableByReservation(Long id);

    List<TableModel> getRestaurantTables(Long id);

    void saveTable(TableModel table);

    void deleteTableById(Long id);
}
