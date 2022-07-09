package com.example.common.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Api(description = "Controller to load custom login page")
@RequestMapping
@RestController
public class LoginController {

    @ApiOperation(value = "Method for login page ")
    @GetMapping(value = "/login")
    public ModelAndView loginPage(){
        return new ModelAndView("login_page");
    }
}
