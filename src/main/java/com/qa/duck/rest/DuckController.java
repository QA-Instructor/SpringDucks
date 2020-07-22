package com.qa.duck.rest;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.duck.dto.DuckDTO;


@RestController
@RequestMapping("/duck")
@CrossOrigin
public class DuckController {

    private DuckService service;

    @Autowired
    public DuckController(DuckService service) {
        super();
        this.service = service;
    }

    @PostMapping("/createDuck")
    public ResponseEntity<Duck> createDuck(@RequestBody Duck duck) {
        return new ResponseEntity<Duck>(this.service.createDuck(duck), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteDuck/{id}")
    public ResponseEntity<Duck> deleteDuck(@PathVariable Long id) {
        return this.service.deleteDuck(id) ? new ResponseEntity<Duck>(HttpStatus.NO_CONTENT) : new ResponseEntity<Duck>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Duck> getDuck(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.findDuckByID(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Duck>> getAllDucks() {
        return ResponseEntity.ok(this.service.readDucks());
    }

    @PutMapping("/updateDuck")
    public ResponseEntity<Duck> updateDuck(@PathParam("id") Long id, @RequestBody Duck duck) {
        return new ResponseEntity<Duck>(this.service.updateDuck(duck, id), HttpStatus.ACCEPTED);
    }

}
