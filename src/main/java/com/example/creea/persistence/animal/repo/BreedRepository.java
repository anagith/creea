package com.example.creea.persistence.animal.repo;

import com.example.creea.persistence.animal.entity.Breed;
import com.example.creea.persistence.animal.entity.Type;
import com.example.creea.persistence.animal.enums.BreedName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BreedRepository extends JpaRepository<Breed, Long> {
    Breed findBreedByName(BreedName breedName);

    List<Breed> findBreedsByType(Type type);
}
