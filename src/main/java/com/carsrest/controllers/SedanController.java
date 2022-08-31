package com.carsrest.controllers;

import com.carsrest.controllers.Exception.NotFoundException;
import com.carsrest.models.Sedan;
import com.carsrest.repositories.SedanRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SedanController {
    private final SedanRepository sedanRepository;

    public SedanController(SedanRepository sedanRepository) {
        this.sedanRepository = sedanRepository;
    }

    @GetMapping("/sedan")
    public ResponseEntity<Iterable<Sedan>> all() {
        return ResponseEntity.ok(sedanRepository.findAll());
    }

    @PostMapping("/sedan")
    public ResponseEntity<?> newObject(@RequestBody Sedan newSedan) {  return ResponseEntity.ok(sedanRepository.save(newSedan)); }

    @GetMapping("/sedan/{id}")
    public ResponseEntity<Sedan> oneObject(@PathVariable Long id) {
        return ResponseEntity.ok(sedanRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id)));
    }

    @DeleteMapping("/sedan/{id}")
    public ResponseEntity<?> deleteObject(@PathVariable Long id) {
        sedanRepository.deleteById(id);
        return ResponseEntity.ok().header("Sedan", "Sedan is deleted").body("");
    }

    @PutMapping("/sedan/{id}")
    Sedan replace(@RequestBody Sedan newObject, @PathVariable Long id) {
        return sedanRepository.findById(id)
                .map(object -> {
                    object.setModel(newObject.getModel() );
                    object.setGeneration(newObject.getGeneration());
                    object.setHeight(newObject.getHeight());
                    object.setWidth(newObject.getWidth());
                    object.setModel(newObject.getModel());
                    object.setSeats(newObject.getSeats());
                    return sedanRepository.save(object);
                })
                .orElseGet(() -> {
                    newObject.setId(id);
                    return sedanRepository.save(newObject);
                });
    }
}