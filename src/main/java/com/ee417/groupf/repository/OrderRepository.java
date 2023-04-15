package com.ee417.groupf.repository;

import com.ee417.groupf.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    List<OrderEntity> findByOrderId(int orderId);
    List<OrderEntity> findByUserId(int userId);
}
