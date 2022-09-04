package com.example.graphql.controller;

import com.example.graphql.model.Car;
import com.example.graphql.repository.CarRepository;
import com.example.graphql.service.car.CarService;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Sort;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.util.annotation.Nullable;

import java.util.Optional;

@Controller
public class CarController {

    private final CarRepository carRepository;
    private final CarService carService;

    public CarController(CarRepository carRepository, CarService carService) {
        this.carRepository = carRepository;
        this.carService = carService;
    }

    @QueryMapping
    public Iterable<Car> cars() {
        return carService.getCars();
    }


    @QueryMapping
    public Car carById(@Argument Long id) {
        return carService.getCar(id);
    }


    @QueryMapping
    public Iterable<Car> carByCost(@Argument Long cost) {
        return carRepository.findByCarCost(cost);
    }

    @QueryMapping
    public Iterable<Car> carByCostGrater(@Argument Long cost) {
        return carRepository.findByCarCostGrater(cost, Sort.by("carCost"));
    }

    @QueryMapping
    public Iterable<Car> carByCostLess(@Argument Long cost) {
        return carRepository.findByCarCostLess(cost, Sort.by("carCost"));
    }

    @QueryMapping
    public Iterable<Car> carByStatus(@Argument String status) {
        return carRepository.findByCarStatus(status, Sort.by("carId"));
    }

    @QueryMapping
    public Iterable<Car> carByModel(@Argument String model) {
        return carService.getCarsByModel(model);
    }

    @QueryMapping
    public Optional<Car> carByMark(@Argument String mark) {
        return carRepository.findByCarMark(mark);
    }

    @QueryMapping
    public Iterable<Car> carByClass(@Argument(value = "class") String carClass) {
        return carService.getCarsByClass(carClass);
    }

    @MutationMapping
    public String addCar(@Argument @NotNull CarInput car) {
        return carService.addCar(new Car(car.carClass, car.model, car.cost, car.mark, car.status));
    }

    @MutationMapping
    public Car addNewCar(@Argument @NotNull CarInput car) {
        return carRepository.save(new Car(car.carClass, car.model, car.cost, car.mark, car.status));
    }
    record CarInput(@Nullable String carClass, @Nullable String model,
                    @Nullable Long cost, @Nullable String mark, @Nullable String status) {

  }

    @MutationMapping
    public String updateCost(@Argument Long id, @Argument Long cost) {
        return carService.updateCarCost(id, cost);
    }

    @MutationMapping
    public String updatePrices(@Argument Long coefficient) {
        return carService.updatePrices(coefficient);
    }


    @MutationMapping
    public String deleteCarById(@Argument Long id) {
        return carService.deleteCarById(id);
    }


    @MutationMapping
    public String updateCarMark(@Argument Long id, @Argument @NotNull CarInput car) {
        return carService.updateCarMark(id,car.model, car.mark);
    }


    @MutationMapping
    public String updateCarFeatures(@Argument(name = "id") Long carId, @Argument @NotNull CarInput car) {

        String message;
        if (car.carClass != null) {
            if (car.mark != null) {
                if (car.cost != null)
                    message = carService.updateCarFeatures(carId, car.model, car.carClass, car.mark, car.cost);
                else
                    message = carService.updateCarFeatures(carId, car.model, car.carClass, car.mark);

            }
            else {
                if (car.cost != null) {
                    message = carService.updateCarFeatures(carId, car.model, car.carClass);
                    carService.updateCarCost(carId, car.cost);
                }
                else
                    message = carService.updateCarFeatures(carId, car.model, car.carClass);
            }
        }else {
            if (car.mark != null) {
                if (car.cost != null) {
                    message = carService.updateCarFeatures(carId, car.model, car.mark);
                    carService.updateCarCost(carId, car.cost);
                } else
                    message = carService.updateCarMark(carId, car.model, car.mark);
            } else {
                if (car.cost != null) {
                    message = carService.updateCarFeatures(carId, car.model);
                    carService.updateCarCost(carId, car.cost);
                }
                else
                    message = carService.updateCarFeatures(carId, car.model);
            }
        }
        return message;
    }


    @MutationMapping
    String updateCarStatus(@Argument Long id, @Argument String status ) {
        return carService.updateCarStatus(id, status);
    }

    record CostProcess(Long cost, String operation) {

    }

    @QueryMapping
    Iterable<Car> carByAvailable(@Argument String status) {
        return carService.getAvailableCars(status);
    }

    @QueryMapping
    Iterable<Car> carByPrice(@Argument @NotNull CostProcess process) {
        return carService.getCarsByCost(process.cost, process.operation);
    }




}
