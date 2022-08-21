package com.example.carrentalservice.controllers.order;

import com.example.carrentalservice.rent_order.RentOrderRequest;
import com.example.carrentalservice.rent_order.RentOrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    private final RentOrderService rentOrderService;

    @PostMapping("/add")
    public String createOrder(Principal principal, @RequestBody RentOrderRequest rentOrderRequest,
                               @RequestParam Long [] carId) {
       return rentOrderService.createOrder(principal, rentOrderRequest,carId);
    }


}
