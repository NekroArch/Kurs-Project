package com.example.admin.service;

import com.example.admin.dto.UserDto;
import com.example.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    List<UserDto> getAllUsers();

    void deleteUser(String name);

    void changeStatusByName(String name, Boolean status);

    void setRole(String name, int roleId);

    void changeRoleByName(String name, int roleId);

    int findUserIdByName(String name);

    User findUserByName(String name);


}
