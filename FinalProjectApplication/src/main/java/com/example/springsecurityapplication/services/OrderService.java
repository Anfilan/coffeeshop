package com.example.springsecurityapplication.services;

import com.example.springsecurityapplication.models.Order;
import com.example.springsecurityapplication.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrder(){
        return orderRepository.findAll();
    }

    public List<Order> getOrderId(int id){
        List<Order> optionalOrder = orderRepository.findById(id);
        return optionalOrder;
    }

    public List<Order> getOrderSorted(int id){
        List<Order> order = orderRepository.findById(id);
        return order;
    }



    @Transactional
    public void updateOrder(int id,Order order){
        order.setId(id);
        orderRepository.save(order);
    }
}

