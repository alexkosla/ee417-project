package com.ee417.groupf.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("orderId")
    private int orderId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonProperty("items")
    private List<ItemEntity> items;

    @Column(name = "user_id")
    @JsonProperty("userId")
    private int userId;

    @Column(name = "date_time")
    @JsonProperty("dateTime")
    private String dateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @JsonProperty("status")
    private Status status;

    @Column(name = "address")
    @JsonProperty("address")
    private String address;

    public enum Status {
        OUT_FOR_DELIVERY,
        ORDERED
    }

    public void addItem(ItemEntity item) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.add(item);
        item.setOrder(this);
    }

    public OrderEntity() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<ItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}