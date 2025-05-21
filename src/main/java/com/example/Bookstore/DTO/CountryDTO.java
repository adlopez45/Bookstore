package com.example.Bookstore.DTO;

import com.example.Bookstore.Models.Country;

public class CountryDTO {
    private Integer countryId;
    private String countryName;

    public CountryDTO(Country country) {
        this.countryId = country.getCountryId();
        this.countryName = country.getCountryName();
    }

    public Integer getCountryId() {
        return countryId;
    }

    public String getCountryName() {
        return countryName;
    }
}
