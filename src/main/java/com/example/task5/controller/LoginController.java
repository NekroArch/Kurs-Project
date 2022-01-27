package com.example.task5.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping
@RestController
public class LoginController {

    @GetMapping(value = "/login")
    public ModelAndView loginPage(){
        return new ModelAndView("login_page");
    }
}
