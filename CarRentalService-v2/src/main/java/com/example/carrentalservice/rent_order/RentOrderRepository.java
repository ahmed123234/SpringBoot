package com.example.carrentalservice.rent_order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentOrderRepository extends JpaRepository<RentOrder, Long> {


}
