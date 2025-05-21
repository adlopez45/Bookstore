package com.example.Bookstore.Models;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "users"})
@Table(name = "roles")
public class Role {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "role_id")
 private Integer roleId;
 @Column(name = "role_name", nullable = false, length = 50)
 private String roleName;
 @Column(name = "status")
 private Byte status;
 @Temporal(TemporalType.TIMESTAMP)
 @Column(name = "created_at")
 private Date createdAt;
 @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
 private List<User> users;
 public Role() {
 }
 public Integer getRoleId() { return roleId; }
 public void setRoleId(Integer roleId) { this.roleId = roleId; }
 public String getRoleName() { return roleName; }
 public void setRoleName(String roleName) { this.roleName = roleName; }
 public Byte getStatus() { return status; }
 public void setStatus(Byte status) { this.status = status; }
 public Date getCreatedAt() { return createdAt; }
 public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
 public List<User> getUsers() { return users; }
 public void setUsers(List<User> users) { this.users = users; }
}