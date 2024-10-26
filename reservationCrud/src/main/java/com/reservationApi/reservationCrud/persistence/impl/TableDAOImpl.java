package com.reservationApi.reservationCrud.persistence.impl;

import com.reservationApi.reservationCrud.models.ReservationModel;
import com.reservationApi.reservationCrud.models.TableModel;
import com.reservationApi.reservationCrud.persistence.ITableDAO;
import com.reservationApi.reservationCrud.repositories.ITableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component

public class TableDAOImpl implements ITableDAO {

    private final ITableRepository tableRepository;

    @Autowired
    public TableDAOImpl(ITableRepository tableRepository){ this.tableRepository = tableRepository;}

    @Override
    public List<TableModel> getTables() {
        return tableRepository.findAll();
    }

    @Override
    public Optional<TableModel> getTableById(Long id) {
        return tableRepository.findTableByIdNative(id);
    }

    @Override
    public Optional<TableModel> getTableByReservation(Long id) {
        return tableRepository.findTableByReservationList_ReservationId(id);
    }

    @Override
    public List<TableModel> getRestaurantTables(Long id) {
        return tableRepository.findByRestaurant_RestaurantId(id);
    }


    @Override
    public void saveTable(TableModel table) {
        tableRepository.save(table);
    }

    @Override
    public void deleteTableById(Long id) {
        tableRepository.deleteById(id);
    }
}
