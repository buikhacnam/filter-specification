package com.buinam.specificationfilter.controller;


import com.buinam.specificationfilter.DTO.UserDTO;
import com.buinam.specificationfilter.model.User;
import com.buinam.specificationfilter.repository.UserRepository;
import com.buinam.specificationfilter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("/allBySpecification") //http://localhost:8080/user/allBySpecification?name=kovacic&age=20&active=
    public List<User> findAllBySpecification(@RequestParam(required = false, defaultValue = "") String name,
                                             @RequestParam(required = false, defaultValue = "") String age,
                                             @RequestParam(required = false, defaultValue = "") String active) {
        return userService.findAllBySpecification(name, age, active);
    }

    @GetMapping("/specific-builder") //http://localhost:8080/user/specific-builder?name=&age=&active=&page=1&size=10&sortBy=&sortDirection=desc
    public Page<User> findAllBySpecificBuilder(@RequestParam(required = false, defaultValue = "") String name,
                                               @RequestParam(required = false) Integer age,
                                               @RequestParam(required = false) Boolean active,
                                               Pageable pageable,
                                               @RequestParam(required = false, defaultValue = "id") String sortBy,
                                               @RequestParam(required = false, defaultValue = "DESC") String sortDirection
    )

    {
        return userService.findAllBySpecificBuilder(name, age, active, pageable, sortBy, sortDirection);
    }

    @PostMapping("/create")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }
}
