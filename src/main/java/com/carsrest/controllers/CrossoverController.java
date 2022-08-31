package com.carsrest.controllers;

import com.carsrest.models.Сrossover;
import com.carsrest.controllers.Exception.NotFoundException;
import com.carsrest.repositories.CrossoverRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CrossoverController {
    private final CrossoverRepository crossoverRepository;

    public CrossoverController(CrossoverRepository crossoverRepository) {
        this.crossoverRepository = crossoverRepository;
    }

    @GetMapping("/crossover")
    public ResponseEntity<Iterable<Сrossover>> all() {
        return ResponseEntity.ok(crossoverRepository.findAll());
    }

    @PostMapping("/crossover")
    public ResponseEntity<?> newObject(@RequestBody Сrossover newObject) {  return ResponseEntity.ok(crossoverRepository.save(newObject)); }

    @GetMapping("/crossover/{id}")
    public ResponseEntity<Сrossover> oneObject(@PathVariable Long id) {
        return ResponseEntity.ok(crossoverRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id)));
    }

    @DeleteMapping("/crossover/{id}")
    public ResponseEntity<?> deleteObject(@PathVariable Long id) {
        crossoverRepository.deleteById(id);
        return ResponseEntity.ok().header("Сrossover", "Сrossover is deleted").body("");
    }

    @PutMapping("/crossover/{id}")
    Сrossover replace(@RequestBody Сrossover newObject, @PathVariable Long id) {
        return crossoverRepository.findById(id)
                .map(object -> {
                    object.setModel(newObject.getModel() );
                    object.setGeneration(newObject.getGeneration());
                    object.setHeight(newObject.getHeight());
                    object.setWidth(newObject.getWidth());
                    object.setModel(newObject.getModel());
                    object.setSeats(newObject.getSeats());
                    object.setClearance(newObject.getClearance());
                    return crossoverRepository.save(object);
                })
                .orElseGet(() -> {
                    newObject.setId(id);
                    return crossoverRepository.save(newObject);
                });
    }
}