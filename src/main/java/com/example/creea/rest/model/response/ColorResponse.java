package com.example.creea.rest.model.response;

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
