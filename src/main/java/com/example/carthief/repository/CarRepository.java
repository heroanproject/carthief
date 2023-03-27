package com.example.carthief.repository;

import com.example.carthief.entity.Car;
import org.springframework.data.repository.ListCrudRepository;

public interface CarRepository extends ListCrudRepository<Car,Long> {
}
