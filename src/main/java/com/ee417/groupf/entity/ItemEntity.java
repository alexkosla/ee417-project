package com.ee417.groupf.entity;

import com.ee417.groupf.model.MenuItemCategoryEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "items")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private OrderEntity order;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Column(name = "quantity")
    @JsonProperty("quantity")
    private int quantity;

    @Column(name = "price")
    @JsonProperty("price")
    private float price;
    @Column(name = "calories")
    private int Calories;
    @Column(name = "pictureLocation")
    private String PictureLocation;
    @Column(name = "description")
    private String Description;
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private MenuItemCategoryEnum Category;

    public ItemEntity() {
    }

    public ItemEntity(Long id, OrderEntity order, String name, int quantity, float price, int calories, String pictureLocation, String description, MenuItemCategoryEnum category) {
        this.id = id;
        this.order = order;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        Calories = calories;
        PictureLocation = pictureLocation;
        Description = description;
        Category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCalories() {
        return Calories;
    }

    public void setCalories(int calories) {
        Calories = calories;
    }

    public String getPictureLocation() {
        return PictureLocation;
    }

    public void setPictureLocation(String pictureLocation) {
        PictureLocation = pictureLocation;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public MenuItemCategoryEnum getCategory() {
        return Category;
    }

    public void setCategory(MenuItemCategoryEnum category) {
        Category = category;
    }
}
