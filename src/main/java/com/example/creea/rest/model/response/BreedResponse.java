package com.example.creea.rest.model.response;



import java.util.ArrayList;
import java.util.List;

public class BreedResponse {
    private List<String> breedNames;

    public BreedResponse() {
        this.breedNames = new ArrayList<>();
    }

    public List<String> getBreedNames() {
        return breedNames;
    }

    public void setBreedNames(List<String> breedNames) {
        this.breedNames = breedNames;
    }
}
