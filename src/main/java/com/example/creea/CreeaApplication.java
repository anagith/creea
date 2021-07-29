package com.example.creea;

import com.example.creea.persistance.animal.TypeRepo;
import com.example.creea.persistance.animal.entity.Type;
import com.example.creea.persistance.animal.enums.TypeName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CreeaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreeaApplication.class, args);
    }
}
