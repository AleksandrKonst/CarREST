package com.carsrest.controllers;

import com.carsrest.controllers.Exception.NotFoundException;
import com.carsrest.models.Engine;
import com.carsrest.repositories.EngineRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EngineController {

    private final EngineRepository engineRepository;

    public EngineController(EngineRepository engineRepository) {
        this.engineRepository = engineRepository;
    }

    @GetMapping("/engine")
    public ResponseEntity<Iterable<Engine>> all() {
        return ResponseEntity.ok(engineRepository.findAll());
    }

    @PostMapping("/engine")
    public ResponseEntity<?> newObject(@RequestBody Engine newObject) {  return ResponseEntity.ok(engineRepository.save(newObject)); }

    @GetMapping("/engine/{id}")
    public ResponseEntity<Engine> oneObject(@PathVariable Long id) {
        return ResponseEntity.ok(engineRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id)));
    }

    @DeleteMapping("/engine/{id}")
    public ResponseEntity<?> deleteObject(@PathVariable Long id) {
        engineRepository.deleteById(id);
        return ResponseEntity.ok().header("Engine", "Engine is deleted").body("");
    }

    @PutMapping("/engine/{id}")
    Engine replace(@RequestBody Engine newObject, @PathVariable Long id) {
        return engineRepository.findById(id)
                .map(object -> {
                    object.setModel(newObject.getModel() );
                    object.setPower(newObject.getPower());
                    object.setTorque(newObject.getTorque());
                    return engineRepository.save(object);
                })
                .orElseGet(() -> {
                    newObject.setId(id);
                    return engineRepository.save(newObject);
                });
    }
}