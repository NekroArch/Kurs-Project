package com.example.task5.service;

import com.example.task5.dto.UserDto;
import com.example.task5.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService{

    List<UserDto> getAllUsers();

    void deleteUser(String name);

    void changeStatusByName(String name, Boolean status);

    void createUser(String name, String password, String email, Boolean turn);

    void setRole(String name, int roleId);

    void changeRoleByName(String name, int roleId);

    int findUserIdByName(String name);

    User findUserByName(String name);
}
