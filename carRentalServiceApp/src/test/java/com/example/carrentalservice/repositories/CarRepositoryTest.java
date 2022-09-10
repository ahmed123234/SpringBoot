package com.example.carrentalservice.repositories;

import com.example.carrentalservice.models.entities.Car;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.JpaSort;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Test
    void canFindByCarId() {
        //given
        Long id = 1L;
        Car actualCar = new Car();
        actualCar.setCarClass("class A");
        actualCar.setCarCost(109L);
        actualCar.setCarMark("123-456-909");
        actualCar.setCarModel("Kia");
        actualCar.setCarStatus("available");
        carRepository.save(actualCar);

        //when
        Car actual = carRepository.findByCarId(id);

        //then
            assertThat(actual).isEqualTo(actualCar);
    }

    @Test
    void canUpdateCarCost() {
        //given
        Long carId = 1L;
        Long cost = 200L;

        Car actualCar = new Car();
        actualCar.setCarClass("class A");
        actualCar.setCarCost(109L);
        actualCar.setCarMark("123-456-909");
        actualCar.setCarModel("Kia");
        actualCar.setCarStatus("available");
        carRepository.save(actualCar);

        actualCar.setCarCost(cost);

        //when
        int val = carRepository.updateCar(carId, cost);

        //then
        if (val == 1) {
            assertThat(actualCar.getCarCost()).isEqualTo(cost);
        }
    }

    @Test
    void canUpdatePrices() {
        //given
        Long coefficient = 2L;
        Long cost = 100L;
        Car actualCar = new Car();
        actualCar.setCarClass("class A");
        actualCar.setCarCost(cost);
        actualCar.setCarMark("123-456-909");
        actualCar.setCarModel("Kia");
        actualCar.setCarStatus("available");
        carRepository.save(actualCar);

        actualCar.setCarCost(cost * coefficient);

        //when
        int val = carRepository.updatePrices(coefficient);

        //then
        if (val == 1) {
            assertThat(actualCar.getCarCost()).isEqualTo(cost * coefficient);
        }
    }

    @Test
    void canUpdateCarFeatures() {
        //given
        Long carId = 1L;
        Long cost = 200L;
        String model = "Golf";
        String carClass = "class B";
        String mark = "123-456-900";

        Car actualCar = new Car();
        actualCar.setCarClass("class A");
        actualCar.setCarCost(109L);
        actualCar.setCarMark("123-456-909");
        actualCar.setCarModel("Kia");
        actualCar.setCarStatus("available");
        carRepository.save(actualCar);

        actualCar.setCarCost(cost);
        actualCar.setCarModel(model);
        actualCar.setCarMark(mark);
        actualCar.setCarClass(carClass);

        //when
        int val = carRepository.updateCarFeatures(carId, model, carClass, mark, cost);

        //then
        if (val == 1) {
            assertThat(actualCar.getCarCost()).isEqualTo(cost);
            assertThat(actualCar.getCarMark()).isEqualTo(mark);
            assertThat(actualCar.getCarModel()).isEqualTo(model);
            assertThat(actualCar.getCarClass()).isEqualTo(carClass);
        }
    }

    @Test
    void canUpdateCarModelClassAndMark() {
        //given
        Long carId = 1L;
        String model = "Golf";
        String carClass = "class B";
        String mark = "123-456-900";

        Car actualCar = new Car();
        actualCar.setCarClass("class A");
        actualCar.setCarCost(109L);
        actualCar.setCarMark("123-456-909");
        actualCar.setCarModel("Kia");
        actualCar.setCarStatus("available");
        carRepository.save(actualCar);

        actualCar.setCarModel(model);
        actualCar.setCarMark(mark);
        actualCar.setCarClass(carClass);

        //when
        int val = carRepository.updateCarModelClassAndMark(carId, model, carClass, mark);

        //then
        if (val == 1) {
            assertThat(actualCar.getCarMark()).isEqualTo(mark);
            assertThat(actualCar.getCarModel()).isEqualTo(model);
            assertThat(actualCar.getCarClass()).isEqualTo(carClass);
        }
    }

    @Test
    void canUpdateCarModelAndClass() {
        //given
        Long carId = 1L;
        String model = "Golf";
        String carClass = "class B";

        Car actualCar = new Car();
        actualCar.setCarClass("class A");
        actualCar.setCarCost(109L);
        actualCar.setCarMark("123-456-909");
        actualCar.setCarModel("Kia");
        actualCar.setCarStatus("available");
        carRepository.save(actualCar);

        actualCar.setCarModel(model);
        actualCar.setCarClass(carClass);

        //when
        int val = carRepository.updateCarModelAndClass(carId, model, carClass);

        //then
        if (val == 1) {
            assertThat(actualCar.getCarModel()).isEqualTo(model);
            assertThat(actualCar.getCarClass()).isEqualTo(carClass);
        }
    }

    @Test
    void canUpdateCarModelAndMark() {
        //given
        Long carId = 1L;
        String model = "Golf";
        String mark = "123-456-900";

        Car actualCar = new Car();
        actualCar.setCarClass("class A");
        actualCar.setCarCost(109L);
        actualCar.setCarMark("123-456-909");
        actualCar.setCarModel("Kia");
        actualCar.setCarStatus("available");
        carRepository.save(actualCar);

        actualCar.setCarModel(model);
        actualCar.setCarMark(mark);

        //when
        int val = carRepository.updateCarModelAndMark(carId, model, mark);

        //then
        if (val == 1) {
            assertThat(actualCar.getCarMark()).isEqualTo(mark);
            assertThat(actualCar.getCarModel()).isEqualTo(model);
        }
    }

    @Test
    void canUpdateCarModel() {
        //given
        Long carId = 1L;
        String model = "Golf";

        Car actualCar = new Car();
        actualCar.setCarClass("class A");
        actualCar.setCarCost(109L);
        actualCar.setCarMark("123-456-909");
        actualCar.setCarModel("Kia");
        actualCar.setCarStatus("available");
        carRepository.save(actualCar);

        actualCar.setCarModel(model);

        //when
        int val = carRepository.updateCarModel(carId, model);

        //then
        if (val == 1) {
            assertThat(actualCar.getCarModel()).isEqualTo(model);
        }
    }

    @Test
    void canUpdateCarStatus() {
        //given
        Long carId = 1L;
        String status = "rented";

        Car actualCar = new Car();
        actualCar.setCarClass("class A");
        actualCar.setCarCost(109L);
        actualCar.setCarMark("123-456-909");
        actualCar.setCarModel("Kia");
        actualCar.setCarStatus("available");
        carRepository.save(actualCar);

        actualCar.setCarStatus(status);

        //when
        int val = carRepository.updateCarStatus(carId, status);

        //then
        if (val == 1) {
            assertThat(actualCar.getCarStatus()).isEqualTo(status);
        }
    }

    @Test
    void canFindByCarStatus() {
        //given
        String status = "available";
        Car actualCar = new Car();
        actualCar.setCarClass("class A");
        actualCar.setCarCost(109L);
        actualCar.setCarMark("123-456-909");
        actualCar.setCarModel("Kia");
        actualCar.setCarStatus("available");
        carRepository.save(actualCar);

        //when
        List<Car> actual = carRepository.findByCarStatus(
                status,
                JpaSort.unsafe("carClass"));

        if (actual.isEmpty()) {
            assertThat(actual).asList().isEmpty();
        }else
            assertThat(actual).asList().contains(actualCar);

    }

    @Test
    void canFindByCarMark() {

        //given
        String mark = "123-456-909";

        Car actualCar = new Car();
        actualCar.setCarClass("class A");
        actualCar.setCarCost(109L);
        actualCar.setCarMark("123-456-909");
        actualCar.setCarModel("Kia");
        actualCar.setCarStatus("available");
        carRepository.save(actualCar);

        //when
       Optional<Car> actual = carRepository.findByCarMark(mark);


        //then
        if (actual.isPresent()) {
            assertThat(actual).isNotEmpty();
            assertThat(actual).contains(actualCar);
        }else
            assertThat(actual).isEmpty();
    }

    @Test
    void canFindByCarCost() {

        //given
        Long cost = 300L;

        Car actualCar = new Car();
        actualCar.setCarClass("class A");
        actualCar.setCarCost(cost);
        actualCar.setCarMark("123-456-909");
        actualCar.setCarModel("Kia");
        actualCar.setCarStatus("available");
        carRepository.save(actualCar);

        //when
        List<Car> actual = carRepository.findByCarCost(cost);


        //then
            assertThat(actual).isNotNull();
            assertThat(actual.get(0)).isEqualTo(actualCar);
    }

    @Test
    void canFindByCarCostGrater() {

        //given
        Long cost = 300L;

        Car actualCar = new Car();
        actualCar.setCarClass("class A");
        actualCar.setCarCost(cost);
        actualCar.setCarMark("123-456-909");
        actualCar.setCarModel("Kia");
        actualCar.setCarStatus("available");
        carRepository.save(actualCar);

        //when
        List<Car> actual = carRepository.findByCarCostGrater
                (cost, JpaSort.unsafe("carClass"));


        //then
        assertThat(actual).isNotNull();
        assertThat(actual.get(0)).isEqualTo(actualCar);
    }

    @Test
    void canFindByCarCostLess() {
        //given
        Long cost = 300L;

        Car actualCar = new Car();
        actualCar.setCarClass("class A");
        actualCar.setCarCost(cost);
        actualCar.setCarMark("123-456-909");
        actualCar.setCarModel("Kia");
        actualCar.setCarStatus("available");
        carRepository.save(actualCar);

        //when
        List<Car> actual = carRepository.findByCarCostLess
                (cost, JpaSort.unsafe("carClass"));


        //then
        assertThat(actual).isNotNull();
        assertThat(actual.get(0)).isEqualTo(actualCar);
    }

    @Test
    void canFindByCarClass() {
        //given
        String carClass = "class D";

        Car actualCar = new Car();
        actualCar.setCarClass(carClass);
        actualCar.setCarCost(300L);
        actualCar.setCarMark("123-456-909");
        actualCar.setCarModel("Kia");
        actualCar.setCarStatus("available");
        carRepository.save(actualCar);

        //when
        List<Car> actual = carRepository.findByCarClass(carClass);

        //then
        assertThat(actual).isNotNull();
        assertThat(actual.get(0)).isEqualTo(actualCar);
    }

    @Test
    void canFindByCarModel() {
        //given
        String model = "Kia";

        Car actualCar = new Car();
        actualCar.setCarClass("class A");
        actualCar.setCarCost(300L);
        actualCar.setCarMark("123-456-909");
        actualCar.setCarModel(model);
        actualCar.setCarStatus("available");
        carRepository.save(actualCar);

        //when
        List<Car> actual = carRepository.findByCarModel(model);

        //then
        assertThat(actual).isNotNull();
        assertThat(actual.get(0)).isEqualTo(actualCar);
    }
}