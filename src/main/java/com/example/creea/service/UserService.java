package com.example.creea.service;

import com.example.creea.persistence.user.entity.User;
import com.example.creea.rest.model.request.UserRequest;
import com.example.creea.rest.model.response.UserResponse;


public interface UserService {
    User create(UserRequest userRequest);

    User getUserById(Long id);

    UserResponse convertEntityToResponse(User user);

    User update(Long id, UserRequest userRequest);

    void delete(Long id);

    void save(User user);
}
