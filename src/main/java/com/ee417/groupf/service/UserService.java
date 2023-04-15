package com.ee417.groupf.service;

import java.util.List;

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
        return userRepository.getAllUsers();
    }


    public UserModel postUser(UserModel userModel) {
        return userRepository.postUsers(userModel);
    }
    
}
