package com.reservationApi.reservationCrud.persistence.impl;

import com.reservationApi.reservationCrud.models.ReservationModel;
import com.reservationApi.reservationCrud.persistence.IReservationDAO;
import com.reservationApi.reservationCrud.repositories.IReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ReservationDAOImpl implements IReservationDAO {

    private final IReservationRepository reservationRepository;

    @Autowired
    public ReservationDAOImpl(IReservationRepository reservationRepository){ this.reservationRepository = reservationRepository;}

    @Override
    public List<ReservationModel> getReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Optional<ReservationModel> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }


    @Override
    public List<ReservationModel> getUserReservations(Long id) {
        return reservationRepository.findByUser_UserId(id);
    }

    @Override
    public void saveReservation(ReservationModel reservation) {
        reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservationById(Long id) {
        reservationRepository.deleteById(id);
    }
}
