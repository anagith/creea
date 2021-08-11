package com.example.creea.service;

import com.example.creea.persistance.animal.entity.Animal;
import com.example.creea.rest.model.AnimalSearchResponse;
import com.example.creea.rest.model.AnimalTypeRequest;
import com.example.creea.security.CustomUserDetails;
import com.example.creea.service.criteria.AnimalFilterModel;
import com.example.creea.rest.model.AnimalRequest;
import com.example.creea.rest.model.AnimalDetailResponse;
import com.example.creea.service.criteria.AnimalPage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface AnimalService {

    Animal create(AnimalRequest animalRequest, Long userId);

    void delete(Long userId, Long animalId);

    Animal get(Long animalId);

    AnimalDetailResponse convertEntityToDetailResponse(Animal animal);

    Animal convertRequestToEntity(AnimalRequest animalRequest, Long userId);

    Animal update(Long id, Long animalId, AnimalRequest animalRequest);

    void deleteByAdmin(Long animalId);

    AnimalSearchResponse filter(AnimalPage animalPage, AnimalFilterModel animalFilterModel);

    Animal uploadImage(String link, Long id, CustomUserDetails customUserDetails);

    String getLink(MultipartFile filePart);

    AnimalSearchResponse searchByType(AnimalPage animalPage, AnimalTypeRequest animalTypeRequest);
}
