package com.example.creea.service.impl;

import com.example.creea.persistance.user.UserRepository;
import com.example.creea.persistance.user.entity.User;
import com.example.creea.persistance.user.enums.UserRole;
import com.example.creea.rest.model.UserRequest;
import com.example.creea.rest.model.UserResponse;
import com.example.creea.service.AnimalService;
import com.example.creea.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public User convertRequestToEntity(UserRequest userRequest) {
        User user = new User();
        setUserPropertiesFromRequest(userRequest, user);
        user.setRole(UserRole.CUSTOMER);
        return user;

    }

    private void setUserPropertiesFromRequest(UserRequest userRequest, User user) {
        user.setName(userRequest.getName());
        user.setUserName(userRequest.getUserName());
        user.setCity(userRequest.getCity());
        user.setAddress(userRequest.getAddress());
        user.setPhone(userRequest.getPhone());
        user.setEmail(userRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(userRequest.getPassword()));
    }

    @Override
    public User create(UserRequest userRequest) {

        User user = convertRequestToEntity(userRequest);
        return userRepository.save(user);

    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getById(id);
    }


    @Override
    public UserResponse convertEntityToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setName(user.getName());
        userResponse.setCity(user.getCity());
        userResponse.setAddress(user.getAddress());
        userResponse.setPhone(user.getPhone());
        userResponse.setEmail(user.getEmail());
        return userResponse;
    }

    @Override
    public User update(Long id, UserRequest userRequest) {
        User user = userRepository.getById(id);
        setUserPropertiesFromRequest(userRequest, user);
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(userRepository.getById(id));
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }


}
