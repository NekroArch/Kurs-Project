package com.example.common.controller;

import com.example.common.dto.UserDto;
import com.example.common.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Api(description = "Controller for registration page")
@RequestMapping
@RestController
public class RegistrationController {

    final private int DEFAULT_USER_ROLE = 2;

    private UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Method to load registration page")
    @GetMapping(value = "/register")
    public ModelAndView registerPage(Model model){
        model.addAttribute("userDto", new UserDto());
        return new ModelAndView("register_page");
    }

    @ApiOperation(value = "Method to save User")
    @PostMapping(value = "/register")
    public ModelAndView saveUser(UserDto userDto){

        userService.createUser(userDto.getName(),
                userDto.getPassword(),
                userDto.getEmail(),
                true);

        userService.setRole(userDto.getName(), DEFAULT_USER_ROLE);
        return new ModelAndView("login_page");
    }

}
