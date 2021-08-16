package com.example.creea.persistance.animal.repo;

import com.example.creea.persistance.animal.entity.Breed;
import com.example.creea.persistance.animal.entity.Type;
import com.example.creea.persistance.animal.enums.BreedName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BreedRepository extends JpaRepository<Breed,Long> {
     Breed findBreedByName(BreedName breedName);
     List<Breed> findBreedsByType(Type type);
}
