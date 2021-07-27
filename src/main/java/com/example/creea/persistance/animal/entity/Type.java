package com.example.creea.persistance.animal.entity;

import com.example.creea.persistance.animal.enums.TypeName;

import javax.persistence.*;

@Entity
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private TypeName type;


    public Type() {
    }

    public long getId() {
        return id;
    }


    public TypeName getType() {
        return type;
    }

    public void setType(TypeName type) {
        this.type = type;
    }
}
