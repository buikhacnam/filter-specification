package com.buinam.specificationfilter.service;

import com.buinam.specificationfilter.DTO.UserDTO;
import com.buinam.specificationfilter.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User create(User user);

    List<User> findAllBySpecification(UserDTO userDTO);
}
