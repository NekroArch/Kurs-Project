package com.example.task5.controller;

import com.example.task5.dto.RoleDto;
import com.example.task5.dto.StatusDto;

import com.example.task5.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Objects;

@RequestMapping(value = "/admin")
@RestController
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ModelAndView getUsers() {
        ModelAndView modelAndView = new ModelAndView("admin_page");
        modelAndView.addObject("userName", SecurityContextHolder.getContext().getAuthentication().getName());
        return modelAndView.addObject("userDto", userService.getAllUsers());
    }

    @DeleteMapping(value = "/delete")
    public void deleteUser(@RequestBody ArrayList<String> loginDto) {
        loginDto.forEach(x -> userService.deleteUser(x));
        loginDto.stream()
                .filter(x -> Objects.equals(x, SecurityContextHolder.getContext().getAuthentication().getName()))
                .forEach(x -> SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false));
    }

    @PatchMapping(value = "/status")
    public void changeStatus(@RequestBody ArrayList<StatusDto> statusDto) {
        statusDto.forEach(x -> userService.changeStatusByName(x.getName(), x.getStatus()));
        statusDto.stream()
                .filter(x -> Objects.equals(x.getName(), SecurityContextHolder.getContext().getAuthentication().getName()))
                .forEach(x -> SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false));
    }

    @PatchMapping(value = "/role")
    public void changeRole(@RequestBody ArrayList<RoleDto> roleDto) {
        roleDto.forEach(x -> userService.changeRoleByName(x.getName(), x.getRoleId()));
    }
}
