package com.ee417.groupf.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ee417.groupf.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import com.ee417.groupf.model.UserModel;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('ADMIN')") // Only allows for ADMIN's to access this endpoint
    @GetMapping("/getUsers")
   public ResponseEntity<List<UserModel>> getAllUsers() {
        System.out.println("--- Get Users ---");
       return ResponseEntity.ok(userService.getAllUsers());
   }
   
   @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
   @GetMapping("/currentUser")
   public ResponseEntity<UserModel> currentUser(Authentication authentication) {
        System.out.println("--- Current Users ---");
       return ResponseEntity.ok(userService.getCurrentUser(authentication.getName()));
   }

    @PostMapping("/postUser")
    public ResponseEntity<UserModel> postUser(@RequestBody UserModel userModel) {
        System.out.println("--- Post User ---");
        return ResponseEntity.created(URI.create("user/created")).body(userService.postUser(userModel));
    }
}
