package com.reservationApi.reservationCrud.controllers.dto;

import com.reservationApi.reservationCrud.models.ReservationModel;
import com.reservationApi.reservationCrud.models.Role;

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
public class UserDTO {
    private Long userId;

    private String name;

    private String cellphone;

    private String email;

    private String password;

    private Role role;

    private List<ReservationModel> reservationList = new ArrayList<>();
}
