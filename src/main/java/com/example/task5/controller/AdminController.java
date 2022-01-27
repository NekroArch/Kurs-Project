package com.example.task5.controller;

import com.example.task5.dto.RoleDto;
import com.example.task5.dto.StatusDto;
import com.example.task5.dto.UserNameDto;
import com.example.task5.service.CollectionService;
import com.example.task5.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequestMapping(value = "/admin")
@RestController
public class AdminController {

    private UserService userService;
    private CollectionService collectionService;
    private ProfileController profileController;

    public AdminController(UserService userService, CollectionService collectionService, ProfileController profileController) {
        this.userService = userService;
        this.collectionService = collectionService;
        this.profileController = profileController;
    }

    @GetMapping()
    public ModelAndView getUsers() {
        ModelAndView modelAndView = new ModelAndView("admin_page");
        modelAndView.addObject("userName", SecurityContextHolder.getContext().getAuthentication().getName());
        return modelAndView.addObject("userDto", userService.getAllUsers());
    }

    @DeleteMapping(value = "/deletes")
    public ModelAndView deleteUser(@RequestBody ArrayList<String> loginDto) {
        loginDto.forEach(x -> userService.deleteUser(x));
        loginDto.stream()
                .filter(x -> Objects.equals(x, SecurityContextHolder.getContext().getAuthentication().getName()))
                .forEach(x -> SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false));
        return new ModelAndView("admin_page");
    }

    @PatchMapping(value = "/status")
    public ModelAndView changeStatus(@RequestBody ArrayList<StatusDto> statusDto) {
        statusDto.forEach(x -> userService.changeStatusByName(x.getName(), x.getStatus()));
        statusDto.stream()
                .filter(x -> Objects.equals(x.getName(), SecurityContextHolder.getContext().getAuthentication().getName()))
                .forEach(x -> SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false));

        return new ModelAndView("admin_page");
    }

    @PatchMapping(value = "/role")
    public ModelAndView changeRole(@RequestBody ArrayList<RoleDto> roleDto) {
        roleDto.forEach(x -> userService.changeRoleByName(x.getName(), x.getRoleId()));
        return new ModelAndView("admin_page");
    }
}
