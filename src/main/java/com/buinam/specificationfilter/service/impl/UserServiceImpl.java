package com.buinam.specificationfilter.service.impl;

import com.buinam.specificationfilter.DTO.UserDTO;
import com.buinam.specificationfilter.model.User;
import com.buinam.specificationfilter.repository.UserRepository;
import com.buinam.specificationfilter.repository.specification.SearchCriteria;
import com.buinam.specificationfilter.repository.specification.UserSpecification;
import com.buinam.specificationfilter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllBySpecification(UserDTO userDTO) {
        /*
                {
                    "name": "kovacic",
                        "age": 20
                }

         */
        UserSpecification userSpecification = new UserSpecification(new SearchCriteria(
                "name", "like", userDTO.getName()
        ));

        UserSpecification userSpecification2 = new UserSpecification(new SearchCriteria(
                "age", "greaterThan", userDTO.getAge()
        ));

        return userRepository.findAll(Specification.where(userSpecification).and(userSpecification2));

    }
}
