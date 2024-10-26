package com.reservationApi.reservationCrud.controllers.dto;

import com.reservationApi.reservationCrud.models.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationDTO {
    private Long reservationId;

    private LocalDate reservationDate;

    private LocalTime reservationHour;

    private Status status;

    private UserModel user;

    private TableModel table;

    private RestaurantModel restaurant;
}
