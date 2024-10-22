package com.reservationApi.reservationCrud.controllers;

import com.reservationApi.reservationCrud.controllers.dto.ReservationDTO;
import com.reservationApi.reservationCrud.controllers.dto.UserDTO;
import com.reservationApi.reservationCrud.models.ReservationModel;
import com.reservationApi.reservationCrud.models.UserModel;
import com.reservationApi.reservationCrud.services.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/reservation")
public class ReservationController {
    private final IReservationService reservationService;

    @Autowired
    public ReservationController(IReservationService reservationService){
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<?> getReservations(){
        List<ReservationDTO> reservationDTOList = reservationService.getReservations()
                .stream()
                .map(this::buildReservationDTO)
                .toList();
        return ResponseEntity.ok(reservationDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReservationById(@PathVariable Long id){
        Optional<ReservationModel> reservationOptional = reservationService.getReservationById(id);
        return createReservationResponseEntity(reservationOptional);
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserReservations(@PathVariable Long id){
        List<ReservationDTO> reservationDTOList = reservationService.getUserReservations(id)
                .stream()
                .map(this::buildReservationDTO)
                .toList();
        return ResponseEntity.ok(reservationDTOList);
    }

    @PostMapping(path="/save")
    public ResponseEntity<?> saveReservation(@RequestBody ReservationDTO reservationDTO) throws URISyntaxException {
        if(reservationDTO.getUser() == null){
            return ResponseEntity.badRequest().build();
        }
        reservationService.saveReservation(buildReservation(reservationDTO));
        return ResponseEntity.created(new URI("api/reservation/save")).build();
    }

    @PutMapping(path="/update/{id}")
    public ResponseEntity<?> updateReservationById(@RequestBody ReservationDTO reservationDTO, @PathVariable Long id){
        Optional<ReservationModel> reservationOptional = reservationService.getReservationById(id);
        if(reservationOptional.isPresent()){
            ReservationModel reservationToUpdate =  reservationOptional.get();
            reservationService.saveReservation(setReservationUpdateValues(reservationDTO, reservationToUpdate));
            return ResponseEntity.ok("Reservacion con id: " + id + " actualizado exitosamente");
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity<?> deleteReservationById(@PathVariable Long id){
        if(id==null){
            reservationService.deleteReservationById(id);
            return ResponseEntity.ok("Reservacion con id " + id + " eliminada exitosamente");
        }
        return ResponseEntity.badRequest().build();
    }

    public ReservationModel setReservationUpdateValues(ReservationDTO reservationDTO, ReservationModel reservationToUpdate){
        reservationToUpdate.setReservationDate(reservationDTO.getReservationDate());
        reservationToUpdate.setReservationHour(reservationDTO.getReservationHour());
        reservationToUpdate.setStatus(reservationDTO.getStatus());
        reservationToUpdate.setUser(reservationDTO.getUser());
        return reservationToUpdate;
    }

    public ResponseEntity<?> createReservationResponseEntity(Optional<ReservationModel> reservationOptional){
        if(reservationOptional.isPresent()){
            ReservationModel reservation = reservationOptional.get();
            return ResponseEntity.ok(buildReservationDTO(reservation));
        }
        return ResponseEntity.notFound().build();
    }

    public ReservationModel buildReservation(ReservationDTO reservationDTO){
        return ReservationModel.builder()
                .reservationId(reservationDTO.getReservationId())
                .reservationDate(reservationDTO.getReservationDate())
                .reservationHour(reservationDTO.getReservationHour())
                .status(reservationDTO.getStatus())
                .user(reservationDTO.getUser())
                .build();
    }

    public ReservationDTO buildReservationDTO(ReservationModel reservation){
        return ReservationDTO.builder()
                .reservationId(reservation.getReservationId())
                .reservationDate(reservation.getReservationDate())
                .reservationHour(reservation.getReservationHour())
                .status(reservation.getStatus())
                .user(reservation.getUser())
                .build();
    }

}
