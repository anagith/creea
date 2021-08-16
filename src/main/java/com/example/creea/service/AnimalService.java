package com.example.creea.service;

import com.example.creea.persistence.animal.entity.Animal;
import com.example.creea.persistence.animal.entity.Breed;
import com.example.creea.rest.model.request.AnimalRequest;
import com.example.creea.rest.model.request.AnimalTypeRequest;
import com.example.creea.rest.model.response.*;
import com.example.creea.security.CustomUserDetails;
import com.example.creea.service.criteria.AnimalFilterModel;
import com.example.creea.service.criteria.AnimalPage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface AnimalService {

    Animal create(AnimalRequest animalRequest, Long userId);

    void delete(Long userId, Long animalId);

    Animal get(Long animalId);

    UserAnimalsResponse getAnimals(Long userId);

    AnimalDetailResponse convertEntityToDetailResponse(Animal animal);

    AnimalResponseForOwner convertEntityToResponseForOwner(Animal animal);

    Animal convertRequestToEntity(AnimalRequest animalRequest, Long userId);

    Animal update(Long id, Long animalId, AnimalRequest animalRequest);

    void deleteByAdmin(Long animalId);

    AnimalSearchResponse filter(AnimalPage animalPage, AnimalFilterModel animalFilterModel);

    Animal uploadImage(String link, Long id, CustomUserDetails customUserDetails);

    String getLink(MultipartFile filePart);

    AnimalSearchResponse searchByType(AnimalPage animalPage, AnimalTypeRequest animalTypeRequest);

    List<Breed> searchBreedsByType(String type);

    BreedResponse convertBreedToBreedResponse(List<Breed> breeds);

    ColorResponse getColors();

    AgeResponse getAges();

    GenderResponse getGenders();

}
