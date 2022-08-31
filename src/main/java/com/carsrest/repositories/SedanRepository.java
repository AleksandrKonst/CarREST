package com.carsrest.repositories;

import com.carsrest.models.Sedan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SedanRepository extends CrudRepository<Sedan,Long> {
}