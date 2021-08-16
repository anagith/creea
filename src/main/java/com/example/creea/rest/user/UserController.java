package com.example.creea.rest.user;

import com.example.creea.persistence.animal.entity.Breed;
import com.example.creea.rest.model.request.AnimalRequest;
import com.example.creea.rest.model.request.AnimalTypeRequest;
import com.example.creea.rest.model.request.UserRequest;
import com.example.creea.rest.model.response.*;
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
import java.util.Locale;

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
    public ResponseEntity<AnimalResponseForOwner> updateAnimal(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long animalId, @RequestBody AnimalRequest animalRequest) {
        AnimalResponseForOwner animalResponse = animalService.convertEntityToResponseForOwner(animalService.update(customUserDetails.getId(), animalId, animalRequest));
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

    @RequestMapping(value = "/search/{type}", method = RequestMethod.GET)
    public ResponseEntity<AnimalSearchResponse> searchByType
            (@PathVariable String type,
             @RequestParam int pageSize,
             @RequestParam int currentPage,
             @RequestParam String sortField,
             @RequestParam String sortDirection) {
        AnimalTypeRequest animalTypeRequest = new AnimalTypeRequest(type);
        AnimalPage animalPage = new AnimalPage(currentPage, pageSize, sortField, sortDirection);
        return new ResponseEntity<>(animalService.searchByType(animalPage, animalTypeRequest),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/{type}/breeds", method = RequestMethod.POST)
    public ResponseEntity<BreedResponse> searchBreedsByType(@PathVariable String type) {
        List<Breed> breeds = animalService.searchBreedsByType(type.toUpperCase(Locale.ROOT));
        return new ResponseEntity<>(animalService.convertBreedToBreedResponse(breeds),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/colors", method = RequestMethod.GET)
    public ResponseEntity<ColorResponse> getColors() {
        ColorResponse colors = animalService.getColors();
        return new ResponseEntity<>(colors, HttpStatus.OK);
    }

    @RequestMapping(value = "/ages", method = RequestMethod.GET)
    public ResponseEntity<AgeResponse> getAges() {
        AgeResponse ages = animalService.getAges();
        return new ResponseEntity<>(ages, HttpStatus.OK);
    }

    @RequestMapping(value = "/genders", method = RequestMethod.GET)
    public ResponseEntity<GenderResponse> getGenders() {
        GenderResponse genders = animalService.getGenders();
        return new ResponseEntity<>(genders, HttpStatus.OK);
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public ResponseEntity<UserResponse> getUser(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Long id = customUserDetails.getId();
        return new ResponseEntity<>(userService.convertEntityToResponse(userService.getUserById(id)),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/myanimals", method = RequestMethod.GET)
    public ResponseEntity<UserAnimalsResponse> getUserAnimals(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Long userId = customUserDetails.getId();
        UserAnimalsResponse animals = animalService.getAnimals(userId);
        return new ResponseEntity<>(animals, HttpStatus.OK);
    }


}
