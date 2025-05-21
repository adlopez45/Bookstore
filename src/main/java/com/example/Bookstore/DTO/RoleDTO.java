package com.example.Bookstore.DTO;

import com.example.Bookstore.Models.Role;

public class RoleDTO {
    private Integer roleId;
    private String roleName;

    public RoleDTO(Role role) {
        this.roleId = role.getRoleId();
        this.roleName = role.getRoleName();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }
}
