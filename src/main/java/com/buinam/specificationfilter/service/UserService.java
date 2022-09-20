package com.buinam.specificationfilter.service;

import com.buinam.specificationfilter.DTO.UserDTO;
import com.buinam.specificationfilter.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User create(User user);

    List<User> findAllBySpecification(String name, String age, String active);

    Page<User> findAllBySpecificBuilder(String name, Integer age, Boolean active, Pageable pageable, String sortBy, String sortDirection);

}
