package com.example.admin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDto {
    private String name;
    private int roleId;

    @Override
    public String toString() {
        return "RoleDto{" +
                "name='" + name + '\'' +
                ", roleId=" + roleId +
                '}';
    }
}
