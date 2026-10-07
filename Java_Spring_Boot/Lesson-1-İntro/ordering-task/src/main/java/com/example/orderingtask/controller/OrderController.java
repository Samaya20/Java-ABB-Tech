package com.example.orderingtask.controller;

import com.example.orderingtask.dto.OrderRequest;
import com.example.orderingtask.dto.OrderResponse;
import com.example.orderingtask.service.OrderCalculationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ordering-task/api/orders")
public class OrderController {
    private OrderCalculationService orderCalculationService;

    public OrderController (OrderCalculationService orderCalculationService){
        this.orderCalculationService = orderCalculationService;
    }

    @PostMapping("/calculate")
    public OrderResponse calculateOrder(@RequestBody OrderRequest request){
        return orderCalculationService.calculate(request);
    }

}
