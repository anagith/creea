package com.example.creea.service.impl;

import com.example.creea.persistance.animal.AnimalRepository;
import com.example.creea.persistance.animal.entity.Animal;
import com.example.creea.persistance.animal.enums.AnimalColor;
import com.example.creea.persistance.animal.enums.AnimalGender;
import com.example.creea.rest.model.AnimalRequest;
import com.example.creea.rest.model.AnimalResponse;
import com.example.creea.service.AnimalService;
import com.example.creea.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class AnimalServiceImpl implements AnimalService {

    private final UserService userService;
    private final AnimalRepository animalRepository;

    public AnimalServiceImpl(UserService userService, AnimalRepository animalRepository) {
        this.userService = userService;
        this.animalRepository = animalRepository;
    }

    @Override
    public Animal create(AnimalRequest animalRequest) {
        Animal animal = convertRequestToEntity(animalRequest);
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

    public Animal convertRequestToEntity(AnimalRequest animalRequest) {
        Animal animal = new Animal();
        animal.setName(animalRequest.getName());
        animal.setAge(animalRequest.getAge());
        animal.setColor(AnimalColor.valueOf(animalRequest.getColour()));
        animal.setGender(AnimalGender.valueOf(animalRequest.getGender()));
        animal.setUser(userService.getUserById(animalRequest.getUserId()));
        return animal;
    }

    public AnimalResponse convertEntityToResponse(Animal animal) {
        AnimalResponse animalResponse = new AnimalResponse();
        animalResponse.setName(animal.getName());
        animalResponse.setAge(animal.getAge());
        animalResponse.setColor(String.valueOf(animal.getColor()));
        animalResponse.setType(String.valueOf(animal.getType()));
        animalResponse.setUserResponse(userService.convertEntityToResponse(animal.getUser()));
        return animalResponse;
    }

}
