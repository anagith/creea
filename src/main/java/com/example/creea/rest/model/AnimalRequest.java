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
    private String color;
    private String gender;
    private String images;
    private String type;

}
