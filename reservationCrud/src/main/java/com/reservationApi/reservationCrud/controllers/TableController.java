package com.reservationApi.reservationCrud.controllers;

import com.reservationApi.reservationCrud.controllers.dto.TableDTO;
import com.reservationApi.reservationCrud.models.TableModel;
import com.reservationApi.reservationCrud.services.ITableService;
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
    public ResponseEntity<?> getTableByReservationId(Long id){
        Optional<TableModel> tableOptional = tableService.getTableByReservation(id);
        return createTableResponseEntity(tableOptional);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<?> getRestaurantTables(@PathVariable Long id){
        List<TableDTO> tableDTOList = tableService.getRestaurantTables(id)
                .stream()
                .map(this::buildTableDTO)
                .toList();
        return ResponseEntity.ok(tableDTOList);
    }

    @PostMapping(path="/save")
    public ResponseEntity<?> saveTable(@RequestBody TableDTO tableDTO) throws URISyntaxException {
        if(tableDTO.getRestaurant() == null){
            return ResponseEntity.badRequest().build();
        }
        tableService.saveTable(buildTable(tableDTO));
        return ResponseEntity.created(new URI("api/table/save")).build();
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<?> updateTableById(@RequestBody TableDTO request, @PathVariable("id") Long id){
        Optional<TableModel> tableOptional = tableService.getTableById(id);
        if(tableOptional.isPresent()){
            TableModel tableToUpdate = tableOptional.get();
            tableService.saveTable(setTableUpdateValues(request, tableToUpdate));
            return ResponseEntity.ok("Mesa con id " + id + " actualizado exitosamente.");
        }
        return ResponseEntity.notFound().build();
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

    public TableModel buildTable(TableDTO tableDTO){
        return TableModel.builder()
                .tableId(tableDTO.getTableId())
                .tableNumber(tableDTO.getTableNumber())
                .capacity(tableDTO.getCapacity())
                .status(tableDTO.getStatus())
                .restaurant(tableDTO.getRestaurant())
                .reservationList(tableDTO.getReservationList())
                .build();
    }

    public TableDTO buildTableDTO(TableModel table){
        return TableDTO.builder()
                .tableId(table.getTableId())
                .tableNumber(table.getTableNumber())
                .capacity(table.getCapacity())
                .status(table.getStatus())
                .restaurant(table.getRestaurant())
                .reservationList(table.getReservationList())
                .build();
    }

    public TableModel setTableUpdateValues(TableDTO tableDTO, TableModel tableToUpdate){
        tableToUpdate.setTableNumber(tableDTO.getTableNumber());
        tableToUpdate.setCapacity(tableDTO.getCapacity());
        tableToUpdate.setStatus(tableDTO.getStatus());
        tableToUpdate.setRestaurant(tableDTO.getRestaurant());
        return tableToUpdate;
    }
}
