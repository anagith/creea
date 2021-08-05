package com.example.creea.rest.animal;

import com.example.creea.persistance.animal.entity.Breed;
import com.example.creea.persistance.animal.entity.Type;
import com.example.creea.persistance.animal.enums.BreedName;
import com.example.creea.persistance.animal.enums.TypeName;
import com.example.creea.persistance.animal.repo.BreedRepository;
import com.example.creea.persistance.animal.repo.TypeRepository;
import com.example.creea.persistance.user.entity.User;
import com.example.creea.persistance.user.enums.UserRole;
import com.example.creea.rest.model.AnimalRequest;
import com.example.creea.rest.model.AnimalResponse;
import com.example.creea.security.CustomUserDetails;
import com.example.creea.service.AnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class AnimalController {


    private final AnimalService animalService;
    private final TypeRepository typeRepo;
    private final BreedRepository breedRepository;

    public AnimalController(AnimalService animalService, TypeRepository typeRepo, BreedRepository breedRepository) {
        this.animalService = animalService;
        this.typeRepo = typeRepo;
        this.breedRepository = breedRepository;
    }

    @RequestMapping(value = "/animal",method = RequestMethod.POST)
    public ResponseEntity<AnimalResponse> createAnimal(@RequestBody AnimalRequest request, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        AnimalResponse animalResponse = animalService.convertEntityToResponse(animalService.create(request,customUserDetails.getId()));
        return new ResponseEntity<>(animalResponse, HttpStatus.OK);
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public void add(){

        Type save1 = typeRepo.save(new Type(TypeName.DOG));
        Type save2 = typeRepo.save( new Type(TypeName.CAT));
        Type save3 = typeRepo.save(new Type(TypeName.BIRD));
        Type save4 = typeRepo.save(new Type(TypeName.FISH));

        breedRepository.save(new Breed(BreedName.DALMATIAN,save1));
        breedRepository.save(new Breed(BreedName.DOBERMAN,save1));
        breedRepository.save(new Breed(BreedName.SIAMESE,save2));

    }

    @RequestMapping(value = "/{animalId}",method = RequestMethod.GET)
    public ResponseEntity<AnimalResponse> getAnimal(@PathVariable Long animalId) {
        AnimalResponse animalResponse = animalService.convertEntityToResponse(animalService.get(animalId));
        return new ResponseEntity<>(animalResponse,HttpStatus.OK);
    }


}
