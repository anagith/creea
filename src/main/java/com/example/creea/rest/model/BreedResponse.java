package com.example.creea.rest.model;



import com.example.creea.persistance.animal.enums.BreedName;

import java.util.List;

public class BreedResponse {
    private List<BreedName> breedNames;

    public List<BreedName> getBreedNames() {
        return breedNames;
    }

    public void setBreedNames(List<BreedName> breedNames) {
        this.breedNames = breedNames;
    }
}
