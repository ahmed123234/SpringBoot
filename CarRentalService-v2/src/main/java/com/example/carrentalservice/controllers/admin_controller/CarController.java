package com.example.carrentalservice.controllers.admin_controller;

import com.example.carrentalservice.car.Car;
import com.example.carrentalservice.car.CarRequest;
import com.example.carrentalservice.car.CarService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/cars/api/v1")
public class CarController {

    private final CarService carService;

    @PostMapping("/add")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addCar(@RequestBody CarRequest carRequest) {

        return carService.addCar(new Car(
                carRequest.getCarClass(),
                carRequest.getCarModel(),
                carRequest.getCarCost(),
                carRequest.getCarMark(),
                "available"
        ));
    }

    @GetMapping("{carId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Car getCar(@PathVariable Long carId) {
        return carService.getCar(carId);
    }

    @GetMapping
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    List<Car> getCars() {
        return carService.getCars();
    }

    @PutMapping("update-cost")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateCarCost(@RequestBody Long carId, Long carCost) {
       return carService.updateCarCost(carId, carCost);
    }
    @PutMapping("update-prices{coefficient}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updatePrices(@PathVariable double coefficient) {
       return carService.updatePrices(coefficient);
    }

    @DeleteMapping("delete{carId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteCarById(@PathVariable Long carId) {
        return carService.deleteCarById(carId);
    }

    @PutMapping("/update{carId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateCarInfo(@PathVariable Long carId,
                                @RequestBody  String carModel,
                                @RequestBody (required = false) String carClass,
                                @RequestBody (required = false) String carMark,
                                @RequestBody (required = false) Long carCost) {
        String message = "";

        if (carClass!=null && carMark!=null &&carCost!=0)
          message = carService.updateCarFeatures(carId, carModel, carClass, carMark, carCost);

        else if (carClass!=null && carMark!=null && carCost==0)
            message = carService.updateCarFeatures(carId, carModel, carClass, carMark);

        else if (carClass!=null && carMark==null && carCost==0)
            message = carService.updateCarFeatures(carId, carModel ,carClass);

        return message;
    }
    @PutMapping("/update-status")
    public String updateCarStatus (@RequestParam Long carId,
                                   @RequestParam String status) {

        carService.updateCarStatus(carId, status);
        return "status updated successfully";
    }
    @GetMapping("available")
    public List<Car> getAvailableCars() {
       return carService.getAvailableCars("available");
    }
}
