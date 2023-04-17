package com.ee417.groupf.service;

import com.ee417.groupf.model.MenuItem;
import com.ee417.groupf.model.MenuItemCategoryEnum;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MenuItemService {
    public List<MenuItem> listMenuItems()
    {
        List<MenuItem> menuItemList = new ArrayList<MenuItem>();

        try (Connection conn = DriverManager
                .getConnection("jdbc:mysql://ee417.crxkzf89o3fh.eu-west-1.rds.amazonaws.com:3306/GroupF2",
                        "EE417", "2023_EE417"))
                // .getConnection("jdbc:mysql://localhost:3306/GroupF",
                // "root", "yourpasswd"))
        {
            PreparedStatement selectStatement = conn.prepareStatement("select * from item");
            ResultSet rs = selectStatement.executeQuery();
            while (rs.next()) {
                int itemId = rs.getInt("item_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                MenuItemCategoryEnum categoryEnum = MenuItemCategoryEnum.valueOf(rs.getString("category"));
                String pictureLocation = rs.getString("picture_location");
                float price = rs.getFloat("price");
                int calories = rs.getInt("calories");

                menuItemList.add(new MenuItem(itemId, name, description, categoryEnum, pictureLocation, price, calories));
            }

            if(menuItemList != null)
            {
                return menuItemList;
            }
        } catch (Exception ex)
        {
            // print the stack trace in case there's an error here reading/writing to file
            ex.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<MenuItem> getMenuItemsByCategory(MenuItemCategoryEnum category)
    {
        List<MenuItem> menuItemList = new ArrayList<MenuItem>();

        try (Connection conn = DriverManager
                .getConnection("jdbc:mysql://ee417.crxkzf89o3fh.eu-west-1.rds.amazonaws.com:3306/GroupF2",
                        "EE417", "2023_EE417"))
                // .getConnection("jdbc:mysql://localhost:3306/GroupF",
                // "root", "yourpasswd"))
        {
            PreparedStatement selectStatement = conn.prepareStatement("select * from item");
            ResultSet rs = selectStatement.executeQuery();
            while (rs.next()) {
                int itemId = rs.getInt("item_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                MenuItemCategoryEnum categoryEnum = MenuItemCategoryEnum.valueOf(rs.getString("category"));
                String pictureLocation = rs.getString("picture_location");
                float price = rs.getFloat("price");
                int calories = rs.getInt("calories");

                menuItemList.add(new MenuItem(itemId, name, description, categoryEnum, pictureLocation, price, calories));
            }

            if(menuItemList != null)
            {
                // similar to a one-liner foreach loop that returns the list of all MenuItems in the list
                // where their category matches the category argument from the controller
                if(category != MenuItemCategoryEnum.All)
                {
                    menuItemList = menuItemList.stream()
                            .filter(item ->  item.getCategory() == category)
                            .collect(Collectors.toList());
                }
                return menuItemList;
            }
        }
        catch (Exception ex)
        {
            // print the stack trace in case there's an error here reading/writing to file
            ex.printStackTrace();
        }
        return Collections.emptyList();
    }

}
