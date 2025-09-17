package com.ecommerce.rutamtb.repository;

import com.ecommerce.rutamtb.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDetail extends JpaRepository<OrderDetail, Long> {
}
