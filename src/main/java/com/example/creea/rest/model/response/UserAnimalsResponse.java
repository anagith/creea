package com.example.creea.rest.model.response;

import java.util.ArrayList;
import java.util.List;

public class UserAnimalsResponse {
    private List<AnimalResponseForOwner> animals;

    public UserAnimalsResponse() {
        this.animals = new ArrayList<>();
    }

    public List<AnimalResponseForOwner> getAnimals() {
        return animals;
    }

    public void setAnimals(List<AnimalResponseForOwner> animals) {
        this.animals = animals;
    }
}
