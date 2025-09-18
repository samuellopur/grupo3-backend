package com.ecommerce.rutamtb.service;

import com.ecommerce.rutamtb.model.OrderDetail;
import com.ecommerce.rutamtb.repository.IOrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService implements IOrderDetailService {

    private IOrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderDetailService(IOrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }
    @Override
    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailRepository.findAll();
    }

    @Override
    public OrderDetail getOrderDetailById(Long id) {
        return orderDetailRepository.findById(id).orElse(null);
    }

    @Override
    public OrderDetail saveOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public void deleteOrderDetail(OrderDetail orderDetail) {
        orderDetailRepository.delete(orderDetail);
    }
}
