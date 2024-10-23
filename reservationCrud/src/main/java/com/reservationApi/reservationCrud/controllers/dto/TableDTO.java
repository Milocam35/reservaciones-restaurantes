package com.reservationApi.reservationCrud.controllers.dto;

import com.reservationApi.reservationCrud.models.ReservationModel;
import com.reservationApi.reservationCrud.models.TableStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TableDTO {

    private Long tableId;

    private Integer tableNumber;

    private Integer capacity;

    private TableStatus status;

    private List<ReservationModel> reservationList = new ArrayList<>();

}
