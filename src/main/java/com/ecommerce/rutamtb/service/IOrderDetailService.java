package com.ecommerce.rutamtb.service;

import com.ecommerce.rutamtb.model.OrderDetail;
import java.util.List;

public interface IOrderDetailService {
    List<OrderDetail> getAllOrderDetails();
    OrderDetail getOrderDetailById(Long id);
    OrderDetail saveOrderDetail(OrderDetail orderDetail);
    void deleteOrderDetail(OrderDetail orderDetail);
}
