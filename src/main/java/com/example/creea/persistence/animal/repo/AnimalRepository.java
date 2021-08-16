package com.example.creea.persistence.animal.repo;

import com.example.creea.persistence.animal.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> getAnimalsByUserId(Long id);
}
