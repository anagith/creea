package com.example.creea.rest.user;

import com.example.creea.rest.model.AnimalRequest;
import com.example.creea.rest.model.AnimalResponse;
import com.example.creea.rest.model.UserRequest;
import com.example.creea.rest.model.UserResponse;
import com.example.creea.security.CustomUserDetails;
import com.example.creea.service.AnimalService;
import com.example.creea.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final AnimalService animalService;
    private final UserService userService;

    public UserController(AnimalService animalService, UserService userService) {
        this.animalService = animalService;
        this.userService = userService;
    }

    @PostMapping("/signUp")
    public UserResponse register(@RequestBody UserRequest userRequest) {
        return userService.convertEntityToResponse(userService.create(userRequest));
    }

    @RequestMapping(value = "delete/{animalId}",method=RequestMethod.DELETE)
    public ResponseEntity<String> deleteAnimal(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long animalId) {
        animalService.delete(customUserDetails.getId(), animalId);
        return new ResponseEntity<>("Successfully deleted!!!", HttpStatus.OK);
    }

    @RequestMapping(value = "update/animal/{animalId}", method = RequestMethod.PUT)
    public ResponseEntity<AnimalResponse> updateAnimal(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long animalId, @RequestBody AnimalRequest animalRequest) {
        AnimalResponse animalResponse = animalService.convertEntityToResponse(animalService.update(customUserDetails.getId(), animalId, animalRequest));
        return new ResponseEntity<>(animalResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "update/user" , method = RequestMethod.PUT)
    public ResponseEntity<UserResponse> updateUser(@AuthenticationPrincipal CustomUserDetails customUserDetails, UserRequest userRequest) {
        UserResponse userResponse = userService.convertEntityToResponse(userService.update(customUserDetails.getId(), userRequest));
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "delete/user", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        userService.delete(customUserDetails.getId());
        return new ResponseEntity<>("Successfully deleted!!!", HttpStatus.OK);
    }
}
