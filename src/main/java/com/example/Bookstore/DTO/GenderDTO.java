package com.example.Bookstore.DTO;

import com.example.Bookstore.Models.Gender;

public class GenderDTO {
    private Integer genderId;
    private String genderName;

    public GenderDTO(Gender gender) {
        this.genderId = gender.getGenderId();
        this.genderName = gender.getGenderName();
    }

    public Integer getGenderId() {
        return genderId;
    }

    public String getGenderName() {
        return genderName;
    }
}
