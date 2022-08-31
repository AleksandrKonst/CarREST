package com.carsrest.controllers;

import com.carsrest.controllers.Exception.NotFoundException;
import com.carsrest.models.Truck;
import com.carsrest.repositories.TruckRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TruckController {
    private final TruckRepository truckRepository;

    public TruckController(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    @GetMapping("/truck")
    public ResponseEntity<Iterable<Truck>> all() {
        return ResponseEntity.ok(truckRepository.findAll());
    }

    @PostMapping("/truck")
    public ResponseEntity<?> newObject(@RequestBody Truck newObject) {  return ResponseEntity.ok(truckRepository.save(newObject)); }

    @GetMapping("/truck/{id}")
    public ResponseEntity<Truck> oneObject(@PathVariable Long id) {
        return ResponseEntity.ok(truckRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id)));
    }

    @DeleteMapping("/truck/{id}")
    public ResponseEntity<?> deleteObject(@PathVariable Long id) {
        truckRepository.deleteById(id);
        return ResponseEntity.ok().header("Truck", "Truck is deleted").body("");
    }

    @PutMapping("/truck/{id}")
    Truck replace(@RequestBody Truck newObject, @PathVariable Long id) {
        return truckRepository.findById(id)
                .map(object -> {
                    object.setModel(newObject.getModel() );
                    object.setGeneration(newObject.getGeneration());
                    object.setHeight(newObject.getHeight());
                    object.setWidth(newObject.getWidth());
                    object.setModel(newObject.getModel());
                    object.setType(newObject.getType());
                    object.setVolume(newObject.getVolume());
                    object.setCarrying_weight(newObject.getCarrying_weight());
                    return truckRepository.save(object);
                })
                .orElseGet(() -> {
                    newObject.setId(id);
                    return truckRepository.save(newObject);
                });
    }
}
