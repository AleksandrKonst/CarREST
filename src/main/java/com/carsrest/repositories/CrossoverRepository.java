package com.carsrest.repositories;

import com.carsrest.models.Сrossover;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrossoverRepository extends CrudRepository<Сrossover,Long> {
}