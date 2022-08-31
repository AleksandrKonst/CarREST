package com.carsrest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "car")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotBlank
    @Size(max=20, message="Name must be less 20 characters long")
    private String model;

    @NotBlank
    @Size(max=20, message="Name must be less 20 characters long")
    private String generation;

    @NotNull
    private int height;

    @NotNull
    private int width;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "engine_id", nullable = false)
    @JsonIgnore
    private Engine engine;

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

}