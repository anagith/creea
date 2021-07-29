package com.example.creea.rest.animal;

import com.example.creea.rest.model.AnimalRequest;
import com.example.creea.rest.model.AnimalResponse;
import com.example.creea.security.CustomUserDetails;
import com.example.creea.service.AnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
public class AnimalController {


    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @RequestMapping(value = "/animal",method = RequestMethod.POST)
    public ResponseEntity<AnimalResponse> createAnimal(@RequestBody AnimalRequest request) {
        AnimalResponse animalResponse = animalService.convertEntityToResponse(animalService.create(request));
        return new ResponseEntity<>(animalResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/{animalId}",method = RequestMethod.GET)
    public ResponseEntity<AnimalResponse> getAnimal(@PathVariable Long animalId) {
        AnimalResponse animalResponse = animalService.convertEntityToResponse(animalService.get(animalId));
        return new ResponseEntity<>(animalResponse,HttpStatus.OK);
    }


}
