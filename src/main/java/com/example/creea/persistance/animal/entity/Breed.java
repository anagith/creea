package com.example.creea.persistance.animal.entity;

import com.example.creea.persistance.animal.enums.BreedName;

import javax.persistence.*;

@Entity
public class Breed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private BreedName name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Type type;

    public Breed() {
    }

    public Breed(BreedName name, Type type) {
        this.name = name;
        this.type = type;
    }
/*
    public long getId() {
        return id;
    }*/

    public BreedName getName() {
        return name;
    }

    public void setName(BreedName name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
