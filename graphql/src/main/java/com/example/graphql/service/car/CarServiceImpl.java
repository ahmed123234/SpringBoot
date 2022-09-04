package com.example.graphql.service.car;

import com.example.graphql.exception.ApiRequestException;
import com.example.graphql.model.Car;
import com.example.graphql.repository.CarRepository;
import com.example.graphql.service.car.CarService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
@Slf4j
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Override
    public String addCar(@NotNull Car car) {
        boolean carMarkExists = carRepository.findByCarMark(car.getCarMark()).isPresent();

        if (carMarkExists) {
            log.error("car Mark: {} already taken", car.getCarMark());
            throw new ApiRequestException("car Mark already taken");
        }

        carRepository.save(car);
        log.info("car: {} added successfully", car.getCarModel());
        return "Car added successfully";
    }

    @Override
    public Car getCar(Long id) {
        log.info("the car with id: {} is returned successfully", id);
        return carRepository.findByCarId(id);
    }

    @Override
    public List<Car> getCars() {
        List<Car> cars = carRepository.findAll();
        log.info("the returned cars is {}", cars);
        return cars;
    }

    @Override
    public String updateCarCost(Long carId, Long carCost) {
        int affected = carRepository.updateCar(carId, carCost);
        if (affected == 0) {
            log.info("No car affected");
            return "No car affected";
        }
        log.info("Car's cost updated Successfully");
        return "Car's cost updated Successfully";
    }

    @Override
    public String updatePrices(Long coefficient) {
        int affected = carRepository.updatePrices(coefficient);
        if (affected == 0) {
            log.info("No car affected");
            return "No car affected";
        }
        log.info("Cars' cost updated Successfully");
        return "Cars' costs updated Successfully";
    }

    @Override
    public String deleteCarById(Long carId) {
        carRepository.deleteById(carId);
        log.info("Car deleted Successfully");
        return "Car deleted Successfully";
    }

    @Override
    public String updateCarMark(Long carId, String carModel, String carMark) {
        int affected = carRepository.updateCarModelAndMark(carId, carModel, carMark);

        if (affected == 0) {
            log.info("No car affected");
            return "No car affected";
        }
        log.info("Car updated Successfully");
        return "Car updated successfully";
    }

    @Override
    public String updateCarFeatures(Long carId, String carModel,
                                    String carClass, String carMark, Long carCost) {
        int affected = carRepository.updateCarFeatures(carId, carModel, carClass, carMark, carCost);
        if (affected == 0) {
            log.info("No car affected");
            return "No car affected";
        }
        log.info("Car updated Successfully");
        return "Car updated successfully";
    }

    @Override
    public String updateCarFeatures(Long carId, String carModel,
                                    String carClass, String carMark) {
        int affected = carRepository.updateCarModelClassAndMark(carId, carModel, carClass, carMark);
        if (affected == 0) {
            log.info("No car affected");
            return "No car affected";
        }
        log.info("Car updated Successfully");
        return "Car updated successfully";
    }

    @Override
    public String updateCarFeatures(Long carId,  String carModel, String carClass) {
        int affected = carRepository.updateCarModelAndClass(carId, carModel, carClass);
        if (affected == 0) {
            log.info("No cars affected");
            return "No cars affected";
        }
        log.info("Car updated Successfully");
        return "Car updated successfully";
    }

    @Override
    public String updateCarFeatures(Long carId,  String carModel) {
        int affected = carRepository.updateCarModel(carId, carModel);
        if (affected == 0) {
            log.info("No cars affected");
            return "No cars affected";
        }
        log.info("Car updated Successfully");
        return "Car updated successfully";
    }

    @Override
    public String updateCarStatus(Long carId, String status) {
        int affected = carRepository.updateCarStatus(carId, status);
        if (affected == 0) {
            log.info("No car affected");
            return "No car affected";
        }
        log.info("Car updated Successfully");
        return "status updated successfully";
    }

    @Override
    public List<Car> getAvailableCars(String status) {
        return carRepository.findByCarStatus(status, Sort.by(Sort.Order.asc("carCost")));
    }

    @Override
    public List<Car> getCarsByCost(Long cost, @NotNull String operation) {

        return switch (operation) {
            case "=" -> carRepository.findByCarCost(cost);
            case ">" -> carRepository.findByCarCostGrater(cost, Sort.by(Sort.Direction.DESC, "carCost"));
            case "<" -> carRepository.findByCarCostLess(cost, Sort.by(Sort.Direction.ASC, "carCost"));
            default -> null;
        };

    }

    @Override
    public List<Car> getCarsByClass(String carClass) {
        return carRepository.findByCarClass(carClass);
    }

    @Override
    public List<Car> getCarsByModel(String model) {
        return carRepository.findByCarModel(model);
    }
}
