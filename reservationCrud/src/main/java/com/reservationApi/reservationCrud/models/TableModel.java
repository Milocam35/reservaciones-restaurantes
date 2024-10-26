package com.reservationApi.reservationCrud.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

@Table(name="`Table`")
public class TableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tableId", nullable = false)
    private Long tableId;

    @Column(name = "tableNumber")
    private Integer tableNumber;

    @Column
    private Integer capacity;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TableStatus status;

    @ManyToOne
    @JoinColumn(name = "restaurantId", nullable = false)
    @JsonIgnore
    private RestaurantModel restaurant;

    @OneToMany(mappedBy = "table", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private List<ReservationModel> reservationList = new ArrayList<>();

}
