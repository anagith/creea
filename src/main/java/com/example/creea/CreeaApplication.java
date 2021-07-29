package com.example.creea;

import com.example.creea.persistance.animal.TypeRepo;
import com.example.creea.persistance.animal.entity.Breed;
import com.example.creea.persistance.animal.entity.Type;
import com.example.creea.persistance.animal.enums.BreedName;
import com.example.creea.persistance.animal.enums.TypeName;
import com.example.creea.persistance.animal.repo.BreedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class CreeaApplication{

    public static void main(String[] args) {

        SpringApplication.run(CreeaApplication.class, args);
    }

}
