package com.ee417.groupf.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ee417.groupf.model.UserModel;
import com.ee417.groupf.repositorys.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public UserModel getCurrentUser(String email){
        return userRepository.findByEmail(email);
    }


    public UserModel postUser(UserModel userModel) {
        String encodedPassword = new BCryptPasswordEncoder().encode(userModel.getPassword()); // Encrypt the password
        userModel.setPassword(encodedPassword); 
        return userRepository.save(userModel);
    }
    
}
