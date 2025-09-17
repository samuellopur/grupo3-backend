package com.ecommerce.rutamtb.repository;

import com.ecommerce.rutamtb.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order, Long> {
}
