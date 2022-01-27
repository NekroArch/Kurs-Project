package com.example.task5.controller;

import com.example.task5.dto.StatusDto;
import com.example.task5.dto.UserDto;
import com.example.task5.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@RequestMapping
@RestController
public class RegistrationController {

    final private int DEFAULT_USER_ROLE = 2;

    private UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/register")
    public ModelAndView registerPage(Model model){
        model.addAttribute("userDto", new UserDto());
        return new ModelAndView("register_page");
    }

    @PostMapping(value = "/register")
    public ModelAndView saveUser(UserDto userDto){

        userService.createUser(userDto.getName(),
                userDto.getPassword(),
                userDto.getEmail(),
                true);

        userService.setRole(userDto.getName(), DEFAULT_USER_ROLE);
        return new ModelAndView("main_page");
    }

}
