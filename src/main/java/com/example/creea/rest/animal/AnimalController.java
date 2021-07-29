package com.example.creea.rest.animal;

import com.example.creea.rest.model.AnimalRequest;
import com.example.creea.rest.model.AnimalResponse;
import com.example.creea.service.AnimalService;
import org.springframework.web.bind.annotation.*;

@RestController
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping("/animal")
    public AnimalResponse createAnimal(@RequestBody AnimalRequest request) {
        return animalService.convertEntityToResponse(animalService.create(request));
    }

    @GetMapping("/{animalId}")
    public AnimalResponse getAnimal(@PathVariable Long animalId) {
        return animalService.convertEntityToResponse(animalService.get(animalId));
    }

}
