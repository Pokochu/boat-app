package com.simple.boat.controller;

import com.simple.boat.model.Boat;
import com.simple.boat.service.BoatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boats")
@RequiredArgsConstructor
public class BoatController {

    private final BoatService boatService;

    @GetMapping
    public ResponseEntity<List<Boat>> getAllBoats() {
        return ResponseEntity.ok(boatService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Boat> getBoat(@PathVariable Long id) {
        return ResponseEntity.ok(boatService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Boat> addBoat(@Valid @RequestBody Boat boat) {
        return new ResponseEntity(boatService.save(new Boat(boat.getName(), boat.getDescription())), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boat> updateBoat(@PathVariable Long id, @Valid @RequestBody Boat boat) {
        return ResponseEntity.ok(boatService.update(id, new Boat(boat.getName(), boat.getDescription())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoat(@PathVariable Long id) {
        boatService.delete(id);
        return ResponseEntity.ok().build();
    }
}
