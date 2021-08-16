package com.example.creea.rest.model.response;

import com.example.creea.persistance.animal.enums.AnimalAge;

import java.util.ArrayList;
import java.util.List;

public class AgeResponse {
    List<String> ages;

    public AgeResponse() {
        this.ages = new ArrayList<>();
    }

    public List<String> getAges() {
        return ages;
    }

    public void setAges(List<String> ages) {
        this.ages = ages;
    }
}
