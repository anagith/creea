package com.example.creea.service.criteria;

public class AnimalPage {

    private Integer pageNumber;
    private Integer pageSize=10;

    private String sortField;
    private String sortDirection;

    public AnimalPage() {
    }

    public AnimalPage(Integer currentPage, Integer pageSize, String sortField, String sortDirection) {
        this.pageNumber = currentPage;
        this.pageSize = pageSize;
        this.sortField = sortField;
        this.sortDirection = sortDirection;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

}
