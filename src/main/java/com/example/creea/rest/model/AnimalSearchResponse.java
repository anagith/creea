package com.example.creea.rest.model;

import java.util.List;

public class AnimalSearchResponse {
    private List<AnimalShortResponse> animals;
    private long totalCount;
    private long totalPage;
    private long currentPage;

    public List<AnimalShortResponse> getAnimals() {
        return animals;
    }

    public void setAnimals(List<AnimalShortResponse> animals) {
        this.animals = animals;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }


    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}

