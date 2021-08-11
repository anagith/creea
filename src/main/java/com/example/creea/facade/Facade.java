package com.example.creea.facade;

import com.example.creea.service.AnimalService;
import com.example.creea.service.UserService;

public class Facade {
    private final UserService userService;
    private final AnimalService animalService;

    public Facade(UserService userService, AnimalService animalService) {
        this.userService = userService;
        this.animalService = animalService;
    }

}
