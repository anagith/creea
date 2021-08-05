package com.example.creea.rest.model;

import com.example.creea.persistance.animal.entity.Type;
import com.example.creea.persistance.animal.enums.AnimalColor;
import com.example.creea.persistance.animal.enums.AnimalGender;
import com.example.creea.persistance.user.entity.User;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;

public class AnimalRequest {
    private String name;
    private int age;
    private String colour;
    private String gender;
    private String type;
    private String breed;

    public AnimalRequest(){}

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}
