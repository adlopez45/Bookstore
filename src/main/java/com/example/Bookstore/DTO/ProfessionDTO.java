package com.example.Bookstore.DTO;

import com.example.Bookstore.Models.Profession;

public class ProfessionDTO {
    private Integer professionId;
    private String professionName;

    public ProfessionDTO(Profession profession) {
        this.professionId = profession.getProfessionId();
        this.professionName = profession.getProfessionName();
    }

    public Integer getProfessionId() {
        return professionId;
    }

    public String getProfessionName() {
        return professionName;
    }
}
