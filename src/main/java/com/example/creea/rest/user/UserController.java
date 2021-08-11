package com.example.creea.rest.user;

import com.example.creea.rest.model.*;
import com.example.creea.security.CustomUserDetails;
import com.example.creea.service.AnimalService;
import com.example.creea.service.UserService;
import com.example.creea.service.criteria.AnimalFilterModel;
import com.example.creea.service.criteria.AnimalPage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final AnimalService animalService;
    private final UserService userService;

    public UserController(AnimalService animalService, UserService userService) {
        this.animalService = animalService;
        this.userService = userService;
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public UserResponse register(@RequestBody UserRequest userRequest) {
        return userService.convertEntityToResponse(userService.create(userRequest));
    }

    @RequestMapping(value = "/{animalId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAnimal(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long animalId) {
        animalService.delete(customUserDetails.getId(), animalId);
        return new ResponseEntity<>("Successfully deleted!!!", HttpStatus.OK);
    }

    @RequestMapping(value = "/animal/{animalId}", method = RequestMethod.PUT)
    public ResponseEntity<AnimalDetailResponse> updateAnimal(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long animalId, @RequestBody AnimalRequest animalRequest) {
        AnimalDetailResponse animalResponse = animalService.convertEntityToDetailResponse(animalService.update(customUserDetails.getId(), animalId, animalRequest));
        return new ResponseEntity<>(animalResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public ResponseEntity<UserResponse> updateUser(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.convertEntityToResponse(userService.update(customUserDetails.getId(), userRequest));
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> AdminDeleteUser(@PathVariable Long userId) {
        userService.delete(userId);
        return new ResponseEntity<>("Successfully deleted!!!", HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/{animalId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> AdminDeleteAnimal(@PathVariable Long animalId) {
        animalService.deleteByAdmin(animalId);
        return new ResponseEntity<>("Successfully deleted!!!", HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        userService.delete(customUserDetails.getId());
        return new ResponseEntity<>("Successfully deleted!!!", HttpStatus.OK);
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public ResponseEntity<AnimalSearchResponse> filterAnimal(
            @RequestParam int pageSize,
            @RequestParam int currentPage,
            @RequestParam String sortField,
            @RequestParam String sortDirection,
            @RequestBody AnimalFilterModel animalFilterModel) {
        AnimalPage animalPage = new AnimalPage(currentPage, pageSize, sortField, sortDirection);
        return new ResponseEntity<>(animalService.filter(animalPage, animalFilterModel),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/search/{type}",method = RequestMethod.GET)
    public ResponseEntity<AnimalSearchResponse> searchByType
            (@PathVariable String type,
             @RequestParam int pageSize,
             @RequestParam int currentPage,
             @RequestParam String sortField,
             @RequestParam String sortDirection){
        AnimalTypeRequest animalTypeRequest = new AnimalTypeRequest(type);
        AnimalPage animalPage = new AnimalPage(currentPage, pageSize, sortField, sortDirection);
        return new ResponseEntity<>(animalService.searchByType(animalPage,animalTypeRequest),
                HttpStatus.OK);
    }
}
