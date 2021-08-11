package com.example.creea.rest.animal;

import com.example.creea.persistance.animal.repo.BreedRepository;
import com.example.creea.persistance.animal.repo.TypeRepository;
import com.example.creea.rest.model.AnimalRequest;
import com.example.creea.rest.model.AnimalDetailResponse;
import com.example.creea.security.CustomUserDetails;
import com.example.creea.service.AnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @RequestMapping(value = "/animal", method = RequestMethod.POST)
    public ResponseEntity<AnimalDetailResponse> createAnimal(@RequestBody AnimalRequest request,
                                                             @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        AnimalDetailResponse animalResponse = animalService.convertEntityToDetailResponse(animalService.create(request, customUserDetails.getId()));
        return new ResponseEntity<>(animalResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<AnimalDetailResponse> uploadImage(@RequestPart MultipartFile filePart,
                                                            @PathVariable Long id,
                                                            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String link = animalService.getLink(filePart);
        AnimalDetailResponse animalResponse = animalService.convertEntityToDetailResponse(animalService.uploadImage(link, id, customUserDetails));
        return new ResponseEntity<>(animalResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/{animalId}", method = RequestMethod.GET)
    public ResponseEntity<AnimalDetailResponse> getAnimal(@PathVariable Long animalId) {
        AnimalDetailResponse animalResponse = animalService.convertEntityToDetailResponse(animalService.get(animalId));
        return new ResponseEntity<>(animalResponse, HttpStatus.OK);
    }



}
