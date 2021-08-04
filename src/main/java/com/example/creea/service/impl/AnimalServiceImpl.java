package com.example.creea.service.impl;

import com.example.creea.persistance.animal.enums.BreedName;
import com.example.creea.persistance.animal.enums.TypeName;
import com.example.creea.persistance.animal.repo.AnimalRepository;
import com.example.creea.persistance.animal.entity.Animal;
import com.example.creea.persistance.animal.enums.AnimalColor;
import com.example.creea.persistance.animal.enums.AnimalGender;
import com.example.creea.persistance.animal.repo.BreedRepository;
import com.example.creea.persistance.animal.repo.TypeRepository;
import com.example.creea.rest.model.AnimalRequest;
import com.example.creea.rest.model.AnimalResponse;
import com.example.creea.security.CustomUserDetails;
import com.example.creea.service.AnimalService;
import com.example.creea.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

@Service
public class AnimalServiceImpl implements AnimalService {

    private final UserService userService;
    private final AnimalRepository animalRepository;
    private final BreedRepository breedRepository;
    private final TypeRepository typeRepository;

    public AnimalServiceImpl(UserService userService, AnimalRepository animalRepository, BreedRepository breedRepository, TypeRepository typeRepository) {
        this.userService = userService;
        this.animalRepository = animalRepository;
        this.breedRepository = breedRepository;
        this.typeRepository = typeRepository;
    }

    @Override
    public Animal create(AnimalRequest animalRequest,Long userId) {
        Animal animal = convertRequestToEntity(animalRequest,userId);
        return animalRepository.save(animal);
    }

    @Override
    public void delete(Long userId, Long animalId) {
        Animal animal = animalRepository.getById(animalId);
        if (animal.getUser().getId().equals(userId)) {
            animalRepository.deleteById(animalId);
        }
    }

    @Override
    public Animal get(Long animalId) {
       return animalRepository.getById(animalId);
    }

    public Animal convertRequestToEntity(AnimalRequest animalRequest,Long userId) {
        Animal animal = new Animal();
        setAnimalPropertiesFromRequest(animalRequest, animal);
        animal.setUser(userService.getUserById(userId));
        return animal;
    }

    private void setAnimalPropertiesFromRequest(AnimalRequest animalRequest, Animal animal) {
        animal.setName(animalRequest.getName());
        animal.setAge(animalRequest.getAge());
        animal.setColor(AnimalColor.valueOf(animalRequest.getColour()));
        animal.setGender(AnimalGender.valueOf(animalRequest.getGender()));
        animal.setBreed(breedRepository.findBreedByName(BreedName.valueOf(animalRequest.getBreed())));
        animal.getBreed().setType(typeRepository.findTypeByType(TypeName.valueOf(animalRequest.getType())));
    }

    @Override
    public Animal update(Long userId, Long animalId, AnimalRequest animalRequest) {
        Animal animal = animalRepository.getById(animalId);
        if (animal.getUser().getId().equals(userId)) {
            setAnimalPropertiesFromRequest(animalRequest, animal);
        }
        return animalRepository.save(animal);
    }


    public AnimalResponse convertEntityToResponse(Animal animal) {
        AnimalResponse animalResponse = new AnimalResponse();
        animalResponse.setId(animal.getId());
        animalResponse.setName(animal.getName());
        animalResponse.setAge(animal.getAge());
        animalResponse.setColor(String.valueOf(animal.getColor()));
        animalResponse.setType(String.valueOf(animal.getBreed().getType().getType()));
        animalResponse.setBreed(String.valueOf(animal.getBreed().getName()));
        animalResponse.setUserResponse(userService.convertEntityToResponse(animal.getUser()));
        return animalResponse;
    }


}
