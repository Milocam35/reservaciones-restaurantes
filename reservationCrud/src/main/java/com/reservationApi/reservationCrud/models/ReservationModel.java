package com.reservationApi.reservationCrud.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name="Reservation")
public class ReservationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="reservationId")
    private Long reservationId;

    @Column(name = "reservationDate")
    private Date reservationDate;

    @Column(name = "reservationHour")
    private LocalTime reservationHour;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @JsonIgnore
    private UserModel user;
}
