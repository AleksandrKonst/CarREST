package com.carsrest.repositories;

import com.carsrest.models.Engine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineRepository extends CrudRepository<Engine,Long> {
}