package com.ee417.groupf.controller;

import com.ee417.groupf.model.MenuItem;
import com.ee417.groupf.model.MenuItemCategoryEnum;
import com.ee417.groupf.service.MenuItemService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenuItemController {

    // create an instance of the MenuItemService, where business logic is performed
    private final MenuItemService service;

    public MenuItemController(MenuItemService service)
    {
        this.service = service;
    }



    // GET endpoint for returning list of all menu items
    @GetMapping("/menu-items")
    @CrossOrigin(origins ="*")
    public ResponseEntity<List<MenuItem>> getList()
    {
        // put business logic in the service, out of the controller
        // returns a list of all menu items saved with an ok message
        return ResponseEntity.ok(service.listMenuItems());
    }

    // GET endpoint for returning list of all menu items with a certain description
    @GetMapping("/menu-items-by-category")
    @CrossOrigin(origins ="*")
    public ResponseEntity<List<MenuItem>> getMenuItemsByCategory(@RequestParam("category") MenuItemCategoryEnum category)
    {
        // put business logic in the service, out of the controller
        return ResponseEntity.ok(service.getMenuItemsByCategory(category));
    }
}
