package com.example.creea.rest.model;

public class AnimalTypeRequest {
    private String type;

    public AnimalTypeRequest(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
