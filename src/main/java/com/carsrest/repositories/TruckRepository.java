package com.carsrest.repositories;

import com.carsrest.models.Truck;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckRepository extends CrudRepository<Truck,Long> {
}