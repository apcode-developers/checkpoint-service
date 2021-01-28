package com.service.checkpoint.web.service.controller;

import com.service.checkpoint.web.service.dto.InventoryDto;
import com.service.checkpoint.web.service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/inventory")
public class inventoryController {

    @Autowired
    private InventoryService service;

    @PostMapping(path = "/addNew")
    public ResponseEntity<?> save(@RequestBody InventoryDto.New value){
        try {
            service.save(value);
            return new ResponseEntity<>(value, HttpStatus.CREATED);

        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        try {
            service.delete(id);
            return new ResponseEntity<>(null, HttpStatus.OK);

        } catch (DataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping ("/update")
    public ResponseEntity<?> update(@RequestBody InventoryDto.Update value){
        try {
            service.update(value);
            return new ResponseEntity<>(value, HttpStatus.CREATED);

        } catch (DataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping ("/findById/{id}")
    public ResponseEntity<?> finByid(@PathVariable("id") String id) {
        try{
            Optional<InventoryDto.Information> arsip = service.findById(id);
            return new ResponseEntity<>(arsip, HttpStatus.OK);
        }
        catch (DataAccessException e){
            e.printStackTrace();
            return  new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping ("/findAll")
    public ResponseEntity<?> findAll(){
        try {
            List<InventoryDto.Information> arsip = service.findAll();
            return new ResponseEntity<>(arsip, HttpStatus.OK);

        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
