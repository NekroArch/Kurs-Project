package com.example.admin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String name;
    private String email;
    private String password;
    private String role;
    private boolean status;

    public UserDto(String name, String role){
        this.name = name;
        this.role = role;
    }

    public UserDto() {

    }

    public UserDto(String name, String email, String password, boolean status, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.status = status;
        this.role = role;
    }

    public UserDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginDto{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role +'\'' +
                '}';
    }
}
