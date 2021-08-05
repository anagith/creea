package com.example.creea.persistance.animal;

import com.example.creea.persistance.animal.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepo extends JpaRepository<Type,Long> {
}
