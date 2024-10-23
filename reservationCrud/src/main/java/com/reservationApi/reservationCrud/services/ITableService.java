package com.reservationApi.reservationCrud.services;

import com.reservationApi.reservationCrud.models.TableModel;

import java.util.List;
import java.util.Optional;

public interface ITableService {
    List<TableModel> getTables();

    Optional<TableModel> getTableById(Long id);

    Optional<TableModel> getTableByReservation(Long id);

    void saveTable(TableModel table);

    void deleteTableById(Long id);
}
