package com.example.creea.service;

import com.example.creea.persistance.animal.entity.Animal;
import com.example.creea.rest.model.AnimalRequest;
import com.example.creea.rest.model.AnimalResponse;

public interface AnimalService {

    Animal create(AnimalRequest animalRequest);
    AnimalResponse convertEntityToResponse(Animal animal);
    Animal convertRequestToEntity(AnimalRequest animalRequest);

}
