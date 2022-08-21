package com.example.carrentalservice.rent_order.rent_order_item;

import com.example.carrentalservice.rent_order.rent_order_item.RentOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentOrderItemRepository extends JpaRepository<RentOrderItem, Long> {


}
