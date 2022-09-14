package com.example.carrentalservice.services.order;

import com.example.carrentalservice.models.entities.Car;
import com.example.carrentalservice.models.entities.RentOrder;
import com.example.carrentalservice.models.entities.RentOrderItem;
import com.example.carrentalservice.models.handelers.RentOrderRequest;
import com.example.carrentalservice.repositories.CarRepository;
import com.example.carrentalservice.repositories.RentOrderItemRepository;
import com.example.carrentalservice.repositories.RentOrderRepository;
import com.example.carrentalservice.services.car.CarServiceImpl;
import com.example.carrentalservice.services.user.AppUserServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static reactor.core.publisher.Mono.when;

@ExtendWith(MockitoExtension.class)
class RentOrderServiceImplTest {

    @Mock private AppUserServiceImpl appUserServiceImpl;
    @Mock private CarServiceImpl carServiceImpl;
    @Mock private RentOrderRepository rentOrderRepository;
    @Mock private RentOrderItemRepository rentOrderItemRepository;

    @Mock private CarRepository carRepository;

    @Autowired
    RentOrderServiceImpl rentOrderService;
    @BeforeEach
    void setUp() {

        rentOrderService  =new RentOrderServiceImpl(
                appUserServiceImpl,
                carServiceImpl,
                rentOrderRepository,
                rentOrderItemRepository
                );

        List<Car>cars = List.of(
                new Car(1L,
                        "class A",
                        "Kia",
                        "123-453-909",
                        200L,
                        "available"));

        carRepository.saveAll(cars);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void canAddOrder() {
        //given
        RentOrder order = new RentOrder(
                1L,
                405,
                "requested",
                "yes",
                new Date(2022, 9,13),
                new Date(2022, 9,16)
        );
        order.setOrderId(1L);
        RentOrder orderSpy = Mockito.mock(RentOrder.class);

        List<Long> carsId = List.of(1L);

        Car car =  new Car(1L,
                "class B",
                "Kia",
                "123-453-989",
                100L,
                "available");

        Car carSpy = Mockito.mock(Car.class);

        //when
        Mockito.when(rentOrderRepository.save(orderSpy).getOrderId()).thenReturn(1L);

        Long orderId = 1L;

        for (Long carId:carsId) {
//            given(carServiceImpl.getCar(carId)).willReturn(car);
            if (car.getCarStatus().equalsIgnoreCase("available")) {
                carServiceImpl.updateCarStatus(carId, "rented");
                RentOrderItem item = new RentOrderItem(orderId, carId);

                rentOrderItemRepository.save(item);
            verify(rentOrderItemRepository).save(item);
            }
        }


        //then
    }

    @Test
    void canCreateOrder() {
        //given
        String username = "ahmad1";

        RentOrderRequest orderRequest = new RentOrderRequest(
                "yes",
                new Date(2022, 9,13),
                new Date(2022, 9,16)
                );

        Long [] carId = new Long[1];
        carId[0] = 1L;

        List<Long> carsId = new ArrayList<>();
        Collections.addAll(carsId, carId);

        RentOrder rentOrder = new RentOrder();
        rentOrder.setOrderId(1L);
        rentOrder.setOrderStatus("requested");
        rentOrder.setOrderDriver(orderRequest.getOrderDriver());
        rentOrder.setOrderStartDate(orderRequest.getOrderStartDate());
        rentOrder.setOrderFinishDate(orderRequest.getOrderFinishDate());
        given(appUserServiceImpl.getUserId(username)).willReturn(1L);
        rentOrder.setUserId(appUserServiceImpl.getUserId(username));

        System.out.println(username + "username of the user want the order");

        String expected= "";
        //when
        long days = ChronoUnit.DAYS.between(rentOrder.getOrderStartDate().toLocalDate(),
                rentOrder.getOrderFinishDate().toLocalDate());

        if (days >= 1) {
            long bill = 0;

            for (Long car : carsId) {
                given(carServiceImpl.getCar(car)).willReturn(new Car(1L,
                        "class B",
                        "Kia",
                        "123-453-989",
                        100L,
                        "available"));

                if(carServiceImpl.getCar(car).getCarStatus().equalsIgnoreCase("available"))
                    bill = bill + (carServiceImpl.getCar(car).getCarCost()) * days;
            }
            if (bill!=0 && rentOrder.getOrderDriver().equals("yes")) {
                bill = bill + (days * 35);
            }
            else if (bill == 0) {

                expected = "The selected Cars is not available right now, please try again later";
            }else {
                rentOrder.setOrderBill((int) bill);
                canAddOrder();
                expected = "Order created successfully";

            }
        } else {
            expected = "Invalid rent duration";
        }
        assertThat(rentOrderService.createOrder(username, orderRequest, carId)).isEqualTo(expected);


            //then

    }

    @Test
    void canGetUserOrders() {
        //given
        Long userId = 1L;

        //when
        rentOrderService.getUserOrders(userId);

        //then
        verify(rentOrderRepository).findAllByUserId(userId);
    }

    @Test
    void canGetOrderById() {
        //given
        Long orderId = 1L;

        //when
        rentOrderService.getOrderById(orderId);

        //then
        verify(rentOrderRepository).findByOrderId(orderId);

    }

    @Test
    void canGetAllOrders() {

        //when
        rentOrderService.getAllOrders();

        //then
        verify(rentOrderRepository).findAllOrders();
    }

    @Test
    void canUpdateOrderStatus() {
        //given
        Long orderId = 1L;
        String status = "requested";

        //when
        rentOrderService.updateOrderStatus(orderId, status);

        //then
        verify(rentOrderRepository).updateOrderStatus(orderId, status);
    }

    @Test
    void canGetOrdersByStatus() {
        //given
        String status = "requested";

        //when
        rentOrderService.getOrdersByStatus(status);

        //then
        verify(rentOrderRepository).findByOrderStatus(status);
    }

    @Test
    void canGetOrderItems() {
        //given
        Long orderId = 1L;

        //when
        rentOrderService.getOrderItems(orderId);

        //then
        verify(rentOrderRepository).getOrderItems(orderId);
    }
}