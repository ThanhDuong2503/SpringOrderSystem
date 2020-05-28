package de.neuefische.springordersystem.controller;

import de.neuefische.springordersystem.model.Order;
import de.neuefische.springordersystem.model.Product;
import de.neuefische.springordersystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ArrayList<Order> listOrders() {
        return orderService.listOrders();
    }


    @PutMapping
    public Order orderProducts(@RequestBody ArrayList<String> productIdsToOrder){
        return orderService.orderProducts(productIdsToOrder);
    }


}
