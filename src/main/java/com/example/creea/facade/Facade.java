package com.example.creea.facade;

import com.example.creea.persistance.animal.entity.Animal;
import com.example.creea.persistance.animal.enums.AnimalColor;
import com.example.creea.persistance.animal.enums.AnimalGender;
import com.example.creea.rest.model.AnimalRequest;
import com.example.creea.rest.model.AnimalResponse;
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
