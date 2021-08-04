package com.example.creea.rest.user;

import com.example.creea.persistance.user.entity.User;
import com.example.creea.persistance.user.enums.UserRole;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final AnimalService animalService;
    private final UserService userService;

    public UserController(AnimalService animalService, UserService userService) {
        this.animalService = animalService;
        this.userService = userService;
    }

   @RequestMapping(value = "/signUp",method = RequestMethod.POST)
    public UserResponse register(@RequestBody UserRequest userRequest) {
        return userService.convertEntityToResponse(userService.create(userRequest));
    }

    @RequestMapping(value = "/{animalId}",method=RequestMethod.DELETE)
    public ResponseEntity<String> deleteAnimal(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long animalId) {
        animalService.delete(customUserDetails.getId(), animalId);
        return new ResponseEntity<>("Successfully deleted!!!", HttpStatus.OK);
    }

    @RequestMapping(value = "/animal/{animalId}", method = RequestMethod.PUT)
    public ResponseEntity<AnimalResponse> updateAnimal(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long animalId, @RequestBody AnimalRequest animalRequest) {
        AnimalResponse animalResponse = animalService.convertEntityToResponse(animalService.update(customUserDetails.getId(), animalId, animalRequest));
        return new ResponseEntity<>(animalResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/user" , method = RequestMethod.PUT)
    public ResponseEntity<UserResponse> updateUser(@AuthenticationPrincipal CustomUserDetails customUserDetails,@RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.convertEntityToResponse(userService.update(customUserDetails.getId(), userRequest));
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
    @RequestMapping(value = "/admin/{userId}", method =RequestMethod.DELETE )
    public ResponseEntity<String> AdminDeleteUser(@PathVariable Long userId){
        userService.delete(userId);
        return new ResponseEntity<>("Successfully deleted!!!",HttpStatus.OK);
    }


    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        userService.delete(customUserDetails.getId());
        return new ResponseEntity<>("Successfully deleted!!!", HttpStatus.OK);
    }
    @RequestMapping(value = "/add/admin",method = RequestMethod.POST)
    public void createAdmin(){
        User user = new User();
        user.setName("Admin");
        user.setRole(UserRole.ADMIN);
        user.setPhone("12345678");
        user.setPassword(new BCryptPasswordEncoder().encode("admin"));
        user.setUserName("admin");
        user.setEmail("admin@gmail.com");
        user.setAddress("dfghj");
        user.setCity("Yerevan");
        userService.save(user);
    }
}
