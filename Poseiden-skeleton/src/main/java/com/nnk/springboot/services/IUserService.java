package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    void createUser(User user);
    List<User> findAll();
    Optional<User> findById(Integer id);
    void updateUser(User user);
    void deleteUser(User user);
}
