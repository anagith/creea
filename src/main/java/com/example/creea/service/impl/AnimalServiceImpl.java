package com.example.creea.service.impl;

import com.example.creea.persistance.animal.entity.Animal;
import com.example.creea.persistance.animal.enums.*;
import com.example.creea.persistance.animal.repo.AnimalCriteriaRepository;
import com.example.creea.persistance.animal.repo.AnimalRepository;
import com.example.creea.persistance.animal.repo.BreedRepository;
import com.example.creea.persistance.animal.repo.TypeRepository;
import com.example.creea.rest.model.AnimalSearchResponse;
import com.example.creea.service.criteria.AnimalFilterModel;
import com.example.creea.rest.model.AnimalRequest;
import com.example.creea.rest.model.AnimalResponse;
import com.example.creea.rest.model.AnimalShortResponse;
import com.example.creea.service.AnimalService;
import com.example.creea.service.UserService;
import com.example.creea.service.criteria.AnimalPage;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service

public class AnimalServiceImpl implements AnimalService {

    private final UserService userService;
    private final AnimalRepository animalRepository;
    private final BreedRepository breedRepository;
    private final TypeRepository typeRepository;
    private final AnimalCriteriaRepository animalCriteriaRepository;

    public AnimalServiceImpl(UserService userService, AnimalRepository animalRepository, BreedRepository breedRepository, TypeRepository typeRepository, AnimalCriteriaRepository animalCriteriaRepository) {
        this.userService = userService;
        this.animalRepository = animalRepository;
        this.breedRepository = breedRepository;
        this.typeRepository = typeRepository;
        this.animalCriteriaRepository = animalCriteriaRepository;
    }

    @Override
    public Animal create(AnimalRequest animalRequest, Long userId) {
        Animal animal = convertRequestToEntity(animalRequest, userId);
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

    public Animal convertRequestToEntity(AnimalRequest animalRequest, Long userId) {
        Animal animal = new Animal();
        setAnimalPropertiesFromRequest(animalRequest, animal);
        animal.setUser(userService.getUserById(userId));
        return animal;
    }

    private void setAnimalPropertiesFromRequest(AnimalRequest animalRequest, Animal animal) {
        animal.setName(animalRequest.getName());
        animal.setAge(AnimalAge.valueOf(animalRequest.getAge()));
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

    @Override
    public void deleteByAdmin(Long animalId) {
        animalRepository.deleteById(animalId);
    }

    @Override
    public AnimalSearchResponse filter(AnimalPage animalPage, AnimalFilterModel animalFilterModel) {
        Page<Animal> page = animalCriteriaRepository.findAllWithFilters(animalPage, animalFilterModel);
        ArrayList<AnimalShortResponse> responses = new ArrayList<>();
        for (Animal animal : page.getContent()) {
            responses.add(convertEntityToResponse(animal));
        }
        AnimalSearchResponse animalSearchResponse = new AnimalSearchResponse();
        animalSearchResponse.setAnimals(responses);
        animalSearchResponse.setCurrentPage(animalPage.getPageNumber());
        animalSearchResponse.setTotalCount(page.getTotalElements());
        animalSearchResponse.setTotalPage(page.getTotalPages());
        return animalSearchResponse;
    }

    public AnimalResponse convertEntityToDetailResponse(Animal animal) {
        AnimalResponse animalResponse = new AnimalResponse();
        animalResponse.setId(animal.getId());
        animalResponse.setName(animal.getName());
        animalResponse.setAge(String.valueOf(animal.getAge()));
        animalResponse.setColor(String.valueOf(animal.getColor()));
        animalResponse.setType(String.valueOf(animal.getBreed().getType().getType()));
        animalResponse.setBreed(String.valueOf(animal.getBreed().getName()));
        animalResponse.setUserResponse(userService.convertEntityToResponse(animal.getUser()));
        return animalResponse;
    }

    public AnimalShortResponse convertEntityToResponse(Animal animal) {
        AnimalShortResponse animalShortResponse = new AnimalShortResponse();
        animalShortResponse.setAge(String.valueOf(animal.getAge()));
        animalShortResponse.setBreed(String.valueOf(animal.getBreed()));
        return animalShortResponse;
    }
}
