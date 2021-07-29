package com.example.creea.persistance.animal.repo;

import com.example.creea.persistance.animal.entity.Breed;
import com.example.creea.persistance.animal.enums.BreedName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;

public interface BreedRepository extends JpaRepository<Breed,Long> {
    public Breed findBreedByName(BreedName breedName);
}
