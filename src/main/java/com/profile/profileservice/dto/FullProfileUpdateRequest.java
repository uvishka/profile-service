package com.profile.profileservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.List;

public class FullProfileUpdateRequest {

    private String companyName;
    private String contactNumber;
    private String owner;

    @Size(max = 255)
    private String address;

    private String description;

    @Size(max = 255)
    private String website;

    @Size(max = 255)
    private String facebookLink;

    @Size(max = 255)
    private String instagramLink;

    @Size(max = 255)
    private String logoUrl;

    @Min(value = 0, message = "numberOfTypesOfBooks cannot be negative")
    private Integer numberOfTypesOfBooks;

    private List<String> genres;

    public FullProfileUpdateRequest() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    public String getInstagramLink() {
        return instagramLink;
    }

    public void setInstagramLink(String instagramLink) {
        this.instagramLink = instagramLink;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Integer getNumberOfTypesOfBooks() {
        return numberOfTypesOfBooks;
    }

    public void setNumberOfTypesOfBooks(Integer numberOfTypesOfBooks) {
        this.numberOfTypesOfBooks = numberOfTypesOfBooks;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
}