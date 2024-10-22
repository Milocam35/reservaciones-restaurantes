package com.reservationApi.reservationCrud.services.impl;

import com.reservationApi.reservationCrud.models.ReservationModel;
import com.reservationApi.reservationCrud.persistence.IReservationDAO;
import com.reservationApi.reservationCrud.services.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements IReservationService {

    private final IReservationDAO reservationDAO;

    @Autowired
    public ReservationServiceImpl(IReservationDAO reservationDAO){
        this.reservationDAO = reservationDAO;
    }

    @Override
    public List<ReservationModel> getReservations() {
        return reservationDAO.getReservations();
    }

    @Override
    public Optional<ReservationModel> getReservationById(Long id) {
        return reservationDAO.getReservationById(id);
    }


    @Override
    public List<ReservationModel> getUserReservations(Long id) {
        return reservationDAO.getUserReservations(id);
    }

    @Override
    public void saveReservation(ReservationModel reservation) {
        reservationDAO.saveReservation(reservation);
    }

    @Override
    public void deleteReservationById(Long id) {
        reservationDAO.deleteReservationById(id);
    }
}
