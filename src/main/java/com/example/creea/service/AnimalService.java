package com.example.creea.service;

import com.example.creea.persistance.animal.entity.Animal;
import com.example.creea.rest.model.AnimalRequest;
import com.example.creea.rest.model.AnimalResponse;

import com.example.creea.persistance.user.entity.User;
import com.example.creea.rest.model.AnimalRequest;
import com.example.creea.rest.model.AnimalResponse;
import org.springframework.stereotype.Service;

@Service
public interface AnimalService {

    Animal create(AnimalRequest animalRequest,Long userId);
    void delete(Long userId, Long animalId);
    Animal get(Long animalId);
    AnimalResponse convertEntityToResponse(Animal animal);
    Animal convertRequestToEntity(AnimalRequest animalRequest,Long userId);

    Animal update(Long id, Long animalId, AnimalRequest animalRequest);
}
