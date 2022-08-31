package com.carsrest.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Entity
@Table(name = "engine")
public class Engine{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max=20, message="Name must be less 20 characters long")
    private String model;

    @NotNull
    private int power;

    @NotNull
    private int torque;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "engine")
    private Set<Car> cars;

    public Set<Car> getCar() {
        return cars;
    }

    public void setCar(Set<Car> cars) {
        this.cars = cars;
    }
}