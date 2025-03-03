package com.example.Bookstore.Models;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "cities")
public class City {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "city_id")
 private Integer cityId;
 @Column(name = "city_name", nullable = false, length = 100)
 private String cityName;
 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "country_id", nullable = false)
 private Country country;
 @Column(name = "status")
 private Byte status;
 @Temporal(TemporalType.TIMESTAMP)
 @Column(name = "created_at")
 private Date createdAt;
 @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
 private List<User> users;
 public City() {
 }
 public Integer getCityId() { return cityId; }
 public void setCityId(Integer cityId) { this.cityId = cityId; }
 public String getCityName() { return cityName; }
 public void setCityName(String cityName) { this.cityName = cityName; }
 public Country getCountry() { return country; }
 public void setCountry(Country country) { this.country = country; }
 public Byte getStatus() { return status; }
 public void setStatus(Byte status) { this.status = status; }
 public Date getCreatedAt() { return createdAt; }
 public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
 public List<User> getUsers() { return users; }
 public void setUsers(List<User> users) { this.users = users; }
}
