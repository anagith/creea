package com.example.creea.service.impl;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.creea.persistance.animal.entity.Animal;
import com.example.creea.persistance.animal.enums.*;
import com.example.creea.persistance.animal.repo.AnimalCriteriaRepository;
import com.example.creea.persistance.animal.repo.AnimalRepository;
import com.example.creea.persistance.animal.repo.BreedRepository;
import com.example.creea.persistance.animal.repo.TypeRepository;
import com.example.creea.rest.model.*;
import com.example.creea.security.CustomUserDetails;
import com.example.creea.service.criteria.AnimalFilterModel;
import com.example.creea.service.AnimalService;
import com.example.creea.service.UserService;
import com.example.creea.service.criteria.AnimalPage;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
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
        animal.setImage(animalRequest.getImage());
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
        ArrayList<AnimalResponse> responses = new ArrayList<>();
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

    @Override
    public Animal uploadImage(String link, Long id, CustomUserDetails customUserDetails) {
        Animal animal = animalRepository.getById(id);
        if(customUserDetails.getId().equals(animal.getUser().getId())){
            animal.setImage(link);
            animalRepository.save(animal);
            return animal;
        }
        return null;
    }

    public String getLink(MultipartFile filePart){
        String accessKey = "AKIATU4YY23K7LINVEHL";
        String secretKey = "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";
        String key = "media/" + filePart.getOriginalFilename();

        BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.EU_CENTRAL_1)
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .build();

        PutObjectRequest putObjectRequest = null;
        try {
            putObjectRequest = new PutObjectRequest(
                    "creea", key, filePart.getInputStream(), new ObjectMetadata()
            );
        } catch (IOException e) {

        }
        putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead);
        client.putObject(putObjectRequest);
        URL url = client.getUrl("creea", key);
        return url.toExternalForm();
    }

    @Override
    public AnimalSearchResponse searchByType(AnimalPage animalPage, AnimalTypeRequest animalTypeRequest){
        Page<Animal> page = animalCriteriaRepository.findAllWithType(animalPage, animalTypeRequest);
        ArrayList<AnimalResponse> responses = new ArrayList<>();
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

    public AnimalDetailResponse convertEntityToDetailResponse(Animal animal) {
        AnimalDetailResponse animalDetailResponse = new AnimalDetailResponse();
        animalDetailResponse.setId(animal.getId());
        animalDetailResponse.setName(animal.getName());
        animalDetailResponse.setAge(String.valueOf(animal.getAge()));
        animalDetailResponse.setImage(animal.getImages());
        animalDetailResponse.setColor(String.valueOf(animal.getColor()));
        animalDetailResponse.setType(String.valueOf(animal.getBreed().getType().getType()));
        animalDetailResponse.setBreed(String.valueOf(animal.getBreed().getName()));
        animalDetailResponse.setUserResponse(userService.convertEntityToResponse(animal.getUser()));
        return animalDetailResponse;
    }

    public AnimalResponse convertEntityToResponse(Animal animal) {
        AnimalResponse animalResponse = new AnimalResponse();
        animalResponse.setId(animal.getId());
        animalResponse.setImage(animal.getImages());
        animalResponse.setAge(String.valueOf(animal.getAge()));
        animalResponse.setBreed(String.valueOf(animal.getBreed().getName()));
        return animalResponse;
    }
}
