package com.example.creea.persistance.animal.repo;

import com.example.creea.persistance.animal.entity.Animal;
import com.example.creea.service.criteria.AnimalFilterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> getAnimalsByUserId(Long id);
}
