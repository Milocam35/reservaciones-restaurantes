package com.reservationApi.reservationCrud.controllers;

import com.reservationApi.reservationCrud.controllers.dto.ReservationDTO;
import com.reservationApi.reservationCrud.controllers.dto.TableDTO;
import com.reservationApi.reservationCrud.models.ReservationModel;
import com.reservationApi.reservationCrud.models.TableModel;
import com.reservationApi.reservationCrud.services.ITableService;
import com.reservationApi.reservationCrud.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("api/table")
public class TableController {
    private final ITableService tableService;

    @Autowired
    public TableController(ITableService tableService){ this.tableService = tableService;}

    @GetMapping
    public ResponseEntity<?> getTables(){
        List<TableDTO> tableDTOList = tableService.getTables()
                .stream()
                .map(this::buildTableDTO)
                .toList();
        return ResponseEntity.ok(tableDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTableById(@PathVariable Long id){
        Optional<TableModel> tableOptional = tableService.getTableById(id);
        return createTableResponseEntity(tableOptional);
    }

    @GetMapping("/reservation/{id}")
    public ResponseEntity<?> getTableBYReservationId(Long id){
        Optional<TableModel> tableOptional = tableService.getTableByReservation(id);
        return createTableResponseEntity(tableOptional);
    }

    @PostMapping(path="/save")
    public ResponseEntity<?> saveTable(@RequestBody TableDTO tableDTO) throws URISyntaxException {
        if(tableDTO.getUser() == null){
            return ResponseEntity.badRequest().build();
        }
        tableService.saveTable(buildTable(tableDTO));
        return ResponseEntity.created(new URI("api/table/save")).build();
    }

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity<?> deleteTableById(@PathVariable Long id){
        if(id==null){
            tableService.deleteTableById(id);
            return ResponseEntity.ok("Mesa con id " + id + " eliminada exitosamente");
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<?> createTableResponseEntity(Optional<TableModel> tableOptional){
        if(tableOptional.isPresent()){
            TableModel table = tableOptional.get();
            return ResponseEntity.ok(buildTableDTO(table));
        }
        return ResponseEntity.notFound().build();
    }

    public TableDTO buildTableDTO(TableModel table){
        return TableDTO.builder()
                .tableId(table.getTableId())
                .tableNumber(table.getTableNumber())
                .capacity(table.getCapacity())
                .status(table.getStatus())
                .reservationList(table.getReservationList())
                .build();
    }
}