package com.ecommerce.rutamtb.service;

import com.ecommerce.rutamtb.model.Order;
import java.util.List;

public interface IOrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    Order saveOrder(Order order);
    void deleteOrder(Order order);
}
