package com.example.Bookstore.DTO;

import com.example.Bookstore.Models.City;

public class CityDTO {
    private Integer cityId;
    private String cityName;

    public CityDTO(City city) {
        this.cityId = city.getCityId();
        this.cityName = city.getCityName();
    }

    public Integer getCityId() { return cityId; }
    public String getCityName() { return cityName; }
}
