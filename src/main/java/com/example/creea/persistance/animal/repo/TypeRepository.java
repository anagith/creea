package com.example.creea.persistance.animal.repo;

import com.example.creea.persistance.animal.entity.Type;
import com.example.creea.persistance.animal.enums.TypeName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type,Long> {
    public Type findTypeByType(TypeName typeName);
}
