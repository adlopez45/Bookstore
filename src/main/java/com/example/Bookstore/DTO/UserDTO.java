package com.example.Bookstore.DTO;

import com.example.Bookstore.Models.User;
import java.text.SimpleDateFormat;

public class UserDTO {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private Integer cityId;
    private Integer countryId;
    private Integer roleId;
    private Integer professionId;
    private Integer genderId;
    private Integer age;
    private Integer status;
    private String createdAt;

    public UserDTO(User user) {
        this.userId = user.getUserId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.cityId = user.getCity() != null ? user.getCity().getCityId() : null;
        this.countryId = user.getCountry() != null ? user.getCountry().getCountryId() : null;
        this.roleId = user.getRole() != null ? user.getRole().getRoleId() : null;
        this.professionId = user.getProfession() != null ? user.getProfession().getProfessionId() : null;
        this.genderId = user.getGender() != null ? user.getGender().getGenderId() : null;
        this.age = user.getAge();
        this.status = user.getStatus() != null ? user.getStatus().intValue() : null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        this.createdAt = user.getCreatedAt() != null ? sdf.format(user.getCreatedAt()) : null;
    }

    // Getters
    public Integer getUserId() { return userId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public Integer getCityId() { return cityId; }
    public Integer getCountryId() { return countryId; }
    public Integer getRoleId() { return roleId; }
    public Integer getProfessionId() { return professionId; }
    public Integer getGenderId() { return genderId; }
    public Integer getAge() { return age; }
    public Integer getStatus() { return status; }
    public String getCreatedAt() { return createdAt; }
}
