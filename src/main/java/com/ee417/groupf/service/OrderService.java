package com.ee417.groupf.service;

import com.ee417.groupf.entity.ItemEntity;
import com.ee417.groupf.entity.OrderEntity;
import com.ee417.groupf.repository.OrderRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public void saveOrder(OrderEntity order) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(order.getOrderId());
        orderEntity.setUserId(order.getUserId());
        orderEntity.setDateTime(order.getDateTime());
        orderEntity.setStatus(order.getStatus());
        orderEntity.setAddress(order.getAddress());

        List<ItemEntity> itemEntities = new ArrayList<>();
        for (ItemEntity item : order.getItems()) {
            ItemEntity itemEntity = new ItemEntity();
            itemEntity.setName(item.getName());
            itemEntity.setQuantity(item.getQuantity());
            itemEntity.setPrice(item.getPrice());
            itemEntity.setCalories(item.getCalories());
            itemEntity.setCategory(item.getCategory());
            itemEntity.setDescription(item.getDescription());
            itemEntity.setPictureLocation(item.getPictureLocation());
            itemEntities.add(itemEntity);
            orderEntity.addItem(itemEntity);
        }

        orderEntity.setItems(itemEntities);
        orderRepository.save(orderEntity);
    }


    public List<OrderEntity> listOrders() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<OrderEntity> orderEntities = orderRepository.findAll();
        return orderEntities.stream().map(orderEntity -> mapper.convertValue(orderEntity, OrderEntity.class))
                .collect(Collectors.toList());
    }


    public List<JsonNode> getOrdersByOrderId(String orderId) throws IOException {
        List<OrderEntity> orderEntities = orderRepository.findByOrderId(Integer.parseInt(orderId));
        ObjectMapper mapper = new ObjectMapper();
        List<JsonNode> jsonNodes = new ArrayList<>();
        for (OrderEntity orderEntity : orderEntities) {
            JsonNode node = mapper.convertValue(orderEntity, JsonNode.class);
            jsonNodes.add(node);
        }
        return jsonNodes;
    }

    public List<JsonNode> getOrdersByUserId(String userId) throws IOException {
        List<OrderEntity> orderEntities = orderRepository.findByUserId(Integer.parseInt(userId));
        ObjectMapper mapper = new ObjectMapper();
        List<JsonNode> jsonNodes = new ArrayList<>();
        for (OrderEntity orderEntity : orderEntities) {
            JsonNode node = mapper.convertValue(orderEntity, JsonNode.class);
            jsonNodes.add(node);
        }
        return jsonNodes;
    }

    public boolean deleteOrderByOrderId(String orderId) throws IOException {
        try {
            orderRepository.deleteById(Integer.parseInt(orderId));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
