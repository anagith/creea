package com.example.creea.service;

import com.example.creea.persistance.animal.entity.Animal;
import com.example.creea.rest.model.AnimalSearchResponse;
import com.example.creea.service.criteria.AnimalFilterModel;
import com.example.creea.rest.model.AnimalRequest;
import com.example.creea.rest.model.AnimalResponse;
import com.example.creea.rest.model.AnimalShortResponse;
import com.example.creea.service.criteria.AnimalPage;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface AnimalService {

    Animal create(AnimalRequest animalRequest, Long userId);

    void delete(Long userId, Long animalId);

    Animal get(Long animalId);

    AnimalResponse convertEntityToDetailResponse(Animal animal);

    Animal convertRequestToEntity(AnimalRequest animalRequest, Long userId);

    Animal update(Long id, Long animalId, AnimalRequest animalRequest);

    void deleteByAdmin(Long animalId);

    AnimalSearchResponse filter(AnimalPage animalPage, AnimalFilterModel animalFilterModel);
}
