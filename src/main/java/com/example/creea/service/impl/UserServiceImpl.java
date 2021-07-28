package com.example.creea.service.impl;

import com.example.creea.persistance.user.UserRepository;
import com.example.creea.persistance.user.entity.User;
import com.example.creea.rest.model.UserRequest;
import com.example.creea.rest.model.UserResponse;
import com.example.creea.service.AnimalService;
import com.example.creea.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public User create(UserRequest userRequest) {
        return null;
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public UserResponse convertEntityToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setName(user.getName());
        userResponse.setCity(user.getCity());
        userResponse.setAddress(user.getAddress());
        userResponse.setEmail(user.getEmail());
        return userResponse;
    }
}
