package com.example.Bookstore.Models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique= true, name = "user_id", nullable= false)
    private Integer userId;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    // Relación con City
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    @JsonManagedReference // Indica que esta parte de la relación es la que se serializa
    private City city;

    // Relación con Country
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    @JsonManagedReference
    private Country country;

    // Relación con Role
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    @JsonManagedReference
    private Role role;

    // Relación con Profession
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profession_id")
    @JsonManagedReference
    private Profession profession;

    // Relación con Gender
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gender_id")
    @JsonManagedReference
    private Gender gender;

    @Column(name = "age")
    private Integer age;

    @Column(name = "status")
    private Byte status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    // Relación con Purchases
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference // Evita la recursión al serializar
    private List<Purchase> purchases;

    // Relación con MembershipCards
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference // Evita la recursión al serializar
    private List<MembershipCard> membershipCards;

    public User() {}

    // Getters y Setters
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public City getCity() { return city; }
    public void setCity(City city) { this.city = city; }

    public Country getCountry() { return country; }
    public void setCountry(Country country) { this.country = country; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public Profession getProfession() { return profession; }
    public void setProfession(Profession profession) { this.profession = profession; }

    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public Byte getStatus() { return status; }
    public void setStatus(Byte status) { this.status = status; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public List<Purchase> getPurchases() { return purchases; }
    public void setPurchases(List<Purchase> purchases) { this.purchases = purchases; }

    public List<MembershipCard> getMembershipCards() { return membershipCards; }
    public void setMembershipCards(List<MembershipCard> membershipCards) { this.membershipCards = membershipCards; }
}
