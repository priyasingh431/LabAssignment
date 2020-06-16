package com.ibm.orderservice.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.orderservice.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
