package com.example.carrentalservice.controllers;

import com.example.carrentalservice.models.entities.Car;
import com.example.carrentalservice.repositories.CarRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GraphQlController {

    private final CarRepository carRepository;

    public GraphQlController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @QueryMapping
    Iterable<Car> cars() {
        return carRepository.findAll();
    }
}
