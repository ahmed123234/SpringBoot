package com.example.carrentalservice.rent_order;

import com.example.carrentalservice.AppUser.AppUserService;
import com.example.carrentalservice.car.CarService;
import com.example.carrentalservice.rent_order.rent_order_item.RentOrderItem;
import com.example.carrentalservice.rent_order.rent_order_item.RentOrderItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class RentOrderService {

    private final AppUserService appUserService;
    private final CarService carService;
    private final RentOrderRepository rentOrderRepository;
    private final RentOrderItemRepository rentOrderItemRepository;

    public void addOrder(RentOrder order, List<Long> carsId) {

        Long orderId = rentOrderRepository.save(order).getOrderId();

        for (Long carId : carsId) {
            if(carService.getCar(carId).getCarStatus().equals("available")) {
                carService.updateCarStatus(carId, "rented");
                RentOrderItem item = new RentOrderItem(orderId, carId);
                rentOrderItemRepository.save(item);
            }
           // else throw new ;
        }
    }

    public String createOrder (Principal principal, RentOrderRequest rentOrderRequest, Long [] carId) {
        UserDetails loginUser = (UserDetails) ((Authentication) principal).getPrincipal();

        String message;
        List<Long> carsId = new ArrayList<>();
        Collections.addAll(carsId, carId);

        RentOrder rentOrder = new RentOrder();
        rentOrder.setOrderStatus("requested");
        rentOrder.setOrderDriver(rentOrderRequest.getOrderDriver());
        rentOrder.setOrderStartDate(rentOrderRequest.getOrderStartDate());
        rentOrder.setOrderFinishDate(rentOrderRequest.getOrderFinishDate());
        rentOrder.setUserId(appUserService.getUserId(loginUser.getUsername()));

        long days = ChronoUnit.DAYS.between(rentOrder.getOrderStartDate().toLocalDate(),
                rentOrder.getOrderFinishDate().toLocalDate());
        if (days >= 1) {
            long bill = 0;

            // calculate the price
            for (Long car : carId) {
                if(carService.getCar(car).getCarStatus().equals("available")) {

                    bill = bill + (carService.getCar(car).getCarCost()) * days;
                }
            }
            if (bill!=0 && rentOrder.getOrderDriver().equals("yes")) {

                bill = bill + (days * 35);
            }
            if (bill == 0)
                return "The selected Cars is not available right now, please try again later";
            rentOrder.setOrderBill((int) bill);
            addOrder(rentOrder, carsId);
            message = "Order created successfully";
        }else
            message = "Invalid rent duration";

        return message;

    }





}
