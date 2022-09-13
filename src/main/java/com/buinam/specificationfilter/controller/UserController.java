package com.buinam.specificationfilter.controller;


import com.buinam.specificationfilter.DTO.UserDTO;
import com.buinam.specificationfilter.model.User;
import com.buinam.specificationfilter.repository.UserRepository;
import com.buinam.specificationfilter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping("/allBySpecification")
    public List<User> findAllBySpecification(@RequestBody UserDTO userDTO) {
        return userService.findAllBySpecification(userDTO);
    }

    @PostMapping("/create")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }
}
