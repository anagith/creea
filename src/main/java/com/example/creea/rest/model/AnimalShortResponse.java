package com.example.creea.rest.model;

public class AnimalShortResponse {
    private Long id;
    private String image;
    private String breed;
    private String  age;

    public AnimalShortResponse() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
