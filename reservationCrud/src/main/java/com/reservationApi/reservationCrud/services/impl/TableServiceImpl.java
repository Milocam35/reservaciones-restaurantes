package com.reservationApi.reservationCrud.services.impl;

import com.reservationApi.reservationCrud.models.TableModel;
import com.reservationApi.reservationCrud.persistence.ITableDAO;
import com.reservationApi.reservationCrud.services.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class TableServiceImpl implements ITableService {
    private final ITableDAO tableDAO;

    @Autowired
    public TableServiceImpl(ITableDAO tableDAO){ this.tableDAO = tableDAO;}

    @Override
    public List<TableModel> getTables() {
        return tableDAO.getTables();
    }

    @Override
    public Optional<TableModel> getTableById(Long id) {
        return tableDAO.getTableById(id);
    }

    @Override
    public Optional<TableModel> getTableByReservation(Long id) {
        return tableDAO.getTableByReservation(id);
    }

    @Override
    public List<TableModel> getRestaurantTables(Long id) {
        return tableDAO.getRestaurantTables(id);
    }


    @Override
    public void saveTable(TableModel table) {
        tableDAO.saveTable(table);
    }

    @Override
    public void deleteTableById(Long id) {
        tableDAO.deleteTableById(id);
    }
}
