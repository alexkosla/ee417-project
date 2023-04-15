package com.ee417.groupf.controller;

import com.ee417.groupf.entity.OrderEntity;
import com.ee417.groupf.service.OrderService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private final OrderService orderService;

    public OrdersController(OrderService orderService) {
        System.out.println("OrdersController===================");
        this.orderService = orderService;
    }

    @PostMapping("/post-order")
    @CrossOrigin(origins = "*")
    public ResponseEntity<OrderEntity> saveOrder(@RequestBody OrderEntity order) throws IOException {
        System.out.println("saveOrder===================");
        orderService.saveOrder(order);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
    }

    @GetMapping("/orderslist")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<OrderEntity>> listOrders() throws IOException {
        System.out.println("listOrders===================");
        List<OrderEntity> orders = orderService.listOrders();
        for (OrderEntity element : orders) {
            System.out.print(element + " ");
        }
        return ResponseEntity.ok(orderService.listOrders());
    }

    @GetMapping("orderslist/{orderId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<JsonNode>> getOrdersByOrderId(@PathVariable String orderId) throws IOException {
        System.out.println("getOrdersByOrderId===================" + orderId);
        System.out.println(orderService.getOrdersByOrderId(orderId));
        return ResponseEntity.ok(orderService.getOrdersByOrderId(orderId));
    }

    @GetMapping("orderslist/user/{userId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<JsonNode>> getOrdersByUserId(@PathVariable String userId) throws IOException {
        System.out.println("getOrdersByUserId===================" + userId);
        System.out.println(orderService.getOrdersByUserId(userId));
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }

    @DeleteMapping("delete/{orderId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Void> deleteOrderByOrderId(@PathVariable String orderId) throws IOException {
        System.out.println("deleteOrderByOrderId===================" + orderId);
        boolean deleted = orderService.deleteOrderByOrderId(orderId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
