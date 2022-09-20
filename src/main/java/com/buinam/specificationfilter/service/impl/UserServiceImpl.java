package com.buinam.specificationfilter.service.impl;

import com.buinam.specificationfilter.model.User;
import com.buinam.specificationfilter.repository.UserRepository;
import com.buinam.specificationfilter.repository.specification.EntitySpecificationsBuilder;
import com.buinam.specificationfilter.repository.specification.SearchCriteria;
import com.buinam.specificationfilter.repository.specification.UserSpecification;
import com.buinam.specificationfilter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<User> findAllBySpecification(String name, String age, String active) {
        /*
                {
                    "name": "kovacic",
                        "age": 20
                }

         */
        UserSpecification userSpecification = new UserSpecification(new SearchCriteria(
                "name", "like", name
        ));

        UserSpecification userSpecification2 = new UserSpecification(new SearchCriteria(
                "age", "greaterThan", age
        ));

        return userRepository.findAll(Specification.where(userSpecification).and(userSpecification2));

    }

    @Override
    public Page<User> findAllBySpecificBuilder(String name, Integer age, Boolean active, Pageable pageable, String sortBy, String sortDirection) {
        User filterUser = new User();
        filterUser.setName(name);
        filterUser.setAge(age);
        filterUser.setActive(active);
        EntitySpecificationsBuilder<User> builder = new EntitySpecificationsBuilder<>();
        if(name != null && !name.isEmpty()) {
            builder.with("name", "like", name);
        }
        if(age != null) {
            builder.with("age", "greaterThan", age);
        }
        if(active != null) {
            builder.with("active", "equal", active);
        }

        Sort sort = Sort.by(Sort.Direction.DESC, sortBy);

        if (sortDirection.equals("asc")) {
            sort = Sort.by(Sort.Direction.ASC, sortBy);
        } 

        Pageable sortedByName =
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        Specification<User> spec = builder.build();
        return userRepository.findAll(spec, sortedByName);
    }

}
