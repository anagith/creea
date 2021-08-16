package com.example.creea.persistence.animal.repo;

import com.example.creea.persistence.animal.entity.Type;
import com.example.creea.persistence.animal.enums.TypeName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
    public Type findTypeByType(TypeName typeName);
}
