package com.bity.bitykart.controller;

import com.bity.bitykart.dto.OrderDto;
import com.bity.bitykart.model.OrderRequest;
import com.bity.bitykart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrderController {

@Autowired
private OrderService orderService;
    @PostMapping("/place/{userId}")
    public OrderDto placeOrder(@PathVariable Long userId, @RequestBody OrderRequest orderRequest){
           return orderService.placeOrder(userId,orderRequest.getProductQuantities(),orderRequest.getTotalAmount());
    }
    @GetMapping("/all-orders")
    public List<OrderDto> getAllOrders(){
        return orderService.getAllOrders();
    }
    @GetMapping("/user/{userId}")
    public List<OrderDto> getOrderByUser(@PathVariable Long userId){
            return orderService.getOrderByUser(userId);
    }
}
