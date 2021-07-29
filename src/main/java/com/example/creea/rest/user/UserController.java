package com.example.creea.rest.user;

import com.example.creea.rest.model.UserRequest;
import com.example.creea.rest.model.UserResponse;
import com.example.creea.service.AnimalService;
import com.example.creea.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final AnimalService animalService;
    private final UserService userService;

    @RequestMapping(value = "delete/{userId}/{animalId}",method=RequestMethod.DELETE)
    public ResponseEntity<String> deleteAnimal(@PathVariable Long userId, @PathVariable Long animalId) {
        animalService.delete(userId, animalId);
        return new ResponseEntity<>("Successfully deleted!!!", HttpStatus.OK);
    }

    public UserController(AnimalService animalService, UserService userService) {
        this.animalService = animalService;
        this.userService = userService;
    }

    @PostMapping("/signUp")
    public UserResponse register(@RequestBody UserRequest userRequest) {
        return userService.convertEntityToResponse(userService.create(userRequest));
    }
}
