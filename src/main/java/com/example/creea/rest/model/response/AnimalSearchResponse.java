package com.example.creea.rest.model.response;

import java.util.List;

public class AnimalSearchResponse {
    private List<AnimalResponse> animals;
    private long totalCount;
    private long totalPage;
    private long currentPage;

    public List<AnimalResponse> getAnimals() {
        return animals;
    }

    public void setAnimals(List<AnimalResponse> animals) {
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

