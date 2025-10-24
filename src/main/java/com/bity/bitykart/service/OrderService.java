package com.bity.bitykart.service;

import com.bity.bitykart.dto.OrderDto;
import com.bity.bitykart.dto.OrderItemDto;
import com.bity.bitykart.model.OrderItem;
import com.bity.bitykart.model.Orders;
import com.bity.bitykart.model.Product;
import com.bity.bitykart.model.User;
import com.bity.bitykart.repository.OrderRepository;
import com.bity.bitykart.repository.ProductRepository;
import com.bity.bitykart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class OrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    public OrderDto placeOrder(Long userId, Map<Long, Integer> productQuantities, double totalAmount) {
        User user=userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("user not found"));
        Orders order=new Orders();
        order.setUser(user);
        order.setOrderdate(new Date());
        order.setStatus("Pending");
        order.setTotalAmount(totalAmount);

        List<OrderItem> orderItems=new ArrayList<>();
        List<OrderItemDto> orderItemDtos=new ArrayList<>();

        for(Map.Entry<Long,Integer> entry:productQuantities.entrySet()){
                Product product=productRepository.findById(entry.getKey())
                        .orElseThrow(()->new RuntimeException("Product not Found"));


                OrderItem orderItem=new OrderItem();
                orderItem.setProduct(product);
                orderItem.setOrder(order);
                orderItem.setQuantity(entry.getValue());
                orderItems.add(orderItem);
                orderItemDtos.add(new OrderItemDto(product.getName(),product.getPrice(),entry.getValue()));
        }
        order.setOrderitems(orderItems);
        Orders saveOrder =orderRepository.save(order);
        return new OrderDto(saveOrder.getId(),saveOrder.getTotalAmount(), saveOrder.getStatus(),
                saveOrder.getOrderdate(),orderItemDtos);

    }

    public List<OrderDto> getAllOrders() {
        List<Orders> orders = orderRepository.findAllOrdersWithUsers();
        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private OrderDto convertToDTO(Orders orders) {
        List<OrderItemDto> orderItems=orders.getOrderitems().stream().
                map(item->new OrderItemDto
                        (item.getProduct().getName(),
                                item.getProduct().getPrice(),
                                item.getQuantity())).collect(Collectors.toList());
        return new OrderDto(orders.getId(),
                            orders.getTotalAmount(),
                              orders.getStatus(),
                              orders.getOrderdate(),
                               orders.getUser()!=null ? orders.getUser().getName():"unknown",
                               orders.getUser()!=null ? orders.getUser().getEmail():"unknown",
                                orderItems
        );
    }

    public List<OrderDto> getOrderByUser(Long userId) {

        Optional<User> userOp = userRepository.findById(userId);
        if(userOp.isEmpty()){
            throw new RuntimeException("User not Found");
        }
        User user=userOp.get();
        List<Orders> orders = orderRepository.findByUser(user);

        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
