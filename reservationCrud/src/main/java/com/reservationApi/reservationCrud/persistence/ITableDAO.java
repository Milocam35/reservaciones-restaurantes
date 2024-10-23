package com.reservationApi.reservationCrud.persistence;

import com.reservationApi.reservationCrud.models.TableModel;

import java.util.List;
import java.util.Optional;

public interface ITableDAO {
    List<TableModel> getTables();

    Optional<TableModel> getTableById(Long id);

    Optional<TableModel> getTableByReservation(Long id);

    void saveTable(TableModel Table);

    void deleteTableById(Long id);
}
