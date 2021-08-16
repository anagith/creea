package com.example.creea.rest.model.response;

import com.example.creea.persistance.animal.enums.AnimalColor;

import java.util.ArrayList;
import java.util.List;

public class ColorResponse {
    List<String> colors;

    public ColorResponse() {
        this.colors = new ArrayList<>();
    }

    public List<String> getColors() {
        return colors;
    }
}
