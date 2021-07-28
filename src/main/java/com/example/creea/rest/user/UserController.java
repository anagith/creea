package com.example.creea.rest.user;

import com.example.creea.persistance.animal.entity.Animal;
import com.example.creea.rest.model.AnimalRequest;
import com.example.creea.rest.model.AnimalResponse;
import com.example.creea.service.AnimalService;
import com.example.creea.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {
    private final AnimalService animalService;
    private final UserService userService;

    public UserController(AnimalService animalService, UserService userService) {
        this.animalService = animalService;
        this.userService = userService;
    }

    @PostMapping("/{userId}/animal")
    public AnimalResponse createAnimal(@PathVariable Long userId, @RequestBody AnimalRequest request){
        return animalService.convertEntityToResponse(animalService.create(request));
    }

}
