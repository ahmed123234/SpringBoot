package com.example.carrentalservice.rent_order;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DailyRentOrder {
    private Long sum;
    private Long count;
    private java.sql.Date start_date;
}
