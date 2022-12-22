package com.orderservice.orderservice.controller;

import com.orderservice.orderservice.dto.OrderRequest;
import com.orderservice.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest)
    {
        System.out.println("-----------------------------------");
        System.out.println("Placing Order");
        System.out.println("-----------------------------------");
        orderService.placeOrder(orderRequest);
        System.out.println("-----------------------------------");
        System.out.println("Order Placed Successfully");
        System.out.println("-----------------------------------");


        return "Order Placed Successfully";
    }

}
