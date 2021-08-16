package com.example.creea.rest.model.response;

import java.util.ArrayList;
import java.util.List;

public class GenderResponse {
    List<String> genders;

    public GenderResponse() {
        this.genders = new ArrayList<>();
    }

    public List<String> getGenders() {
        return genders;
    }
}
