package com.example.carrentalservice.repositories;

import com.example.carrentalservice.models.entities.Car;
import com.example.carrentalservice.models.entities.RentOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RentOrderRepositoryTest {

    @Autowired
    private RentOrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        List<RentOrder> orders = List.of(
                new RentOrder(
                        2L,
                        200,
                        "requested",
                        "yes", Date.valueOf(LocalDate.now()),
                        Date.valueOf(LocalDate.now().plusDays(4))),

                new RentOrder(1L,
                        600,
                        "rented",
                        "yes",
                        Date.valueOf(LocalDate.now()),
                        Date.valueOf(LocalDate.now().plusDays(2))),

                new RentOrder(
                        2L,
                        100,
                        "requested",
                        "yes", Date.valueOf(LocalDate.now()),
                        Date.valueOf(LocalDate.now().plusDays(7)))

                );

        orderRepository.saveAll(orders);
    }

    @AfterEach
    void tearDown() {
        orderRepository.deleteAll();
    }

    @Test
    void canFindAllByUserId() {
        //given
        Long userId = 1L;
        List<RentOrder> orders = List.of(
                new RentOrder(
                        1L,
                        200,
                        "requested",
                        "yes",
                        Date.valueOf(LocalDate.now()),
                        Date.valueOf(LocalDate.now().plusDays(4))
                ),
                new RentOrder(
                        1L,
                        300,
                        "requested",
                        "yes",
                        Date.valueOf(LocalDate.now()),
                        Date.valueOf(LocalDate.now().plusDays(10))
                )
        );

        orderRepository.saveAll(orders);

        //when
        Iterable<RentOrder> actual = orderRepository.findAllByUserId(userId);

        AtomicInteger validIdFound = new AtomicInteger();
        actual.forEach(
                rentOrder -> {
                    if (rentOrder.getOrderId() > 0) {
                        validIdFound.getAndIncrement();
                    }
                }
        );
        assertThat(validIdFound.intValue()).isEqualTo(3);
    }

    @Test
    void canFindByOrderId() {

        //given
        Long orderId = 1L;

        //when
        RentOrder actual = orderRepository.findByOrderId(orderId);

        AtomicInteger validIdFound = new AtomicInteger();
        if(actual.getOrderId() > 0)
            validIdFound.getAndIncrement();

        assertThat(validIdFound.intValue()).isEqualTo(1);

    }

    @Test
    void canUpdateOrderStatus() {
        //given
        String status = "rented";
        Long orderId = 1L;

        //when
        int actual = orderRepository.updateOrderStatus(orderId, status);

        AtomicInteger validIdFound = new AtomicInteger();

        if(actual == 1) {
            validIdFound.getAndIncrement();
        }
        assertThat(actual).isEqualTo(validIdFound.intValue());
    }

    @Test
    void canFindByOrderStatus() {
        //given
        String status = "requested";

        //when
        Iterable<RentOrder> actual = orderRepository.findByOrderStatus(status);

        AtomicInteger validIdFound = new AtomicInteger();
        actual.forEach(
                rentOrder -> {
                    if (rentOrder.getOrderId() > 0) {
                        validIdFound.getAndIncrement();
                    }
                }
        );
        assertThat(validIdFound.intValue()).isEqualTo(2);
    }

    @Test
    void canFindAllOrders() {
        //when
        Iterable<RentOrder> actual = orderRepository.findAllOrders();

        AtomicInteger validIdFound = new AtomicInteger();
        actual.forEach(
                rentOrder -> {
                    if (rentOrder.getOrderId() > 0) {
                        validIdFound.getAndIncrement();
                    }
                }
        );
        assertThat(validIdFound.intValue()).isEqualTo(3);
    }

    @Test
    void canGetOrderItems() {
        //given
        Long orderId = 2L;

        //when
        Iterable<Car> actual = orderRepository.getOrderItems(orderId);

        AtomicInteger validIdFound = new AtomicInteger();
        actual.forEach(
                car -> {
                    if (car.getCarId() > 0) {
                        validIdFound.getAndIncrement();
                    }
                }
        );
        assertThat(validIdFound.intValue()).isEqualTo(0);
    }
}