package com.ee417.groupf.model;

import java.util.UUID;
public class MenuItem {
    private int MenuItemId;

    private String Name;

    private String Description;

    private MenuItemCategoryEnum Category;

    private String PictureLocation;

    private float Price;

    private int Calories;

    public MenuItem(int menuItemId, String name, String description, MenuItemCategoryEnum category, String pictureLocation, float price, int calories) {
        MenuItemId = menuItemId;
        Name = name;
        Description = description;
        Category = category;
        PictureLocation = pictureLocation;
        Price = price;
        Calories = calories;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getMenuItemId() {
        return MenuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        MenuItemId = menuItemId;
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

    public String getPictureLocation() {
        return PictureLocation;
    }

    public void setPictureLocation(String pictureLocation) {
        PictureLocation = pictureLocation;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public int getCalories() {
        return Calories;
    }

    public void setCalories(int calories) {
        Calories = calories;
    }
}
