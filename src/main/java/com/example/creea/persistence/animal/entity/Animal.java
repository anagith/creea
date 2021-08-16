package com.example.creea.persistence.animal.entity;

import com.example.creea.persistence.animal.enums.AnimalAge;
import com.example.creea.persistence.animal.enums.AnimalColor;
import com.example.creea.persistence.animal.enums.AnimalGender;
import com.example.creea.persistence.user.entity.User;

import javax.persistence.*;

@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AnimalAge age;

    private AnimalColor color;
    @Column(nullable = false)

    private AnimalGender gender;
    //@Column(nullable=false)
    private String images;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Breed breed;

    public Animal() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AnimalAge getAge() {
        return age;
    }

    public void setAge(AnimalAge age) {
        this.age = age;
    }

    public AnimalColor getColor() {
        return color;
    }

    public void setColor(AnimalColor color) {
        this.color = color;
    }

    public AnimalGender getGender() {
        return gender;
    }

    public void setGender(AnimalGender gender) {
        this.gender = gender;
    }

    public String getImages() {
        return images;
    }

    public void setImage(String images) {
        this.images = images;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }
}
