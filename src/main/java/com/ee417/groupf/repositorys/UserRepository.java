package com.ee417.groupf.repositorys;

import java.util.List;
import com.ee417.groupf.model.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Integer> {

    List<UserModel> findAll();

    UserModel findByEmail(String email);

    List<UserModel> findById(int id);

}
