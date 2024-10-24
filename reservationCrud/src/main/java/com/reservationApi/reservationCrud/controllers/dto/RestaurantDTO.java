package com.reservationApi.reservationCrud.controllers.dto;

import com.reservationApi.reservationCrud.models.ReservationModel;
import com.reservationApi.reservationCrud.models.TableModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class RestaurantDTO {

    private Long restaurantId;

    private String name;

    private String address;

    private String phone;

    private LocalTime openingHour;

    private LocalTime closingHour;

    private List<ReservationModel> reservationList = new ArrayList<>();

    private List<TableModel> tableList = new ArrayList<>();

}
