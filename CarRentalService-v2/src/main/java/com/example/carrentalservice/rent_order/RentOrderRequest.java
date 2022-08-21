package com.example.carrentalservice.rent_order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.sql.Date;

@AllArgsConstructor
@Getter
@Setter
public class RentOrderRequest {

    private String orderDriver;
    private Date orderStartDate;
    private Date orderFinishDate;

}
