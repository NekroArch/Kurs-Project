package com.example.task5.controller;

import com.example.task5.dto.UserDto;
import com.example.task5.repository.UserRepository;
import com.example.task5.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;
import java.util.stream.Collectors;


@RequestMapping
@RestController
public class MainController {

    private UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public ModelAndView mainPage(){
        ModelAndView modelAndView = new ModelAndView("main_page");
        if(Objects.equals(SecurityContextHolder.getContext().getAuthentication().getName(), "anonymousUser")){
            return modelAndView.addObject("anonymousUser", "anonymousUser");

        }else if("[ROLE_USER]".equals(userService.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName())
                .getRoles().stream()
                .map(x -> new SimpleGrantedAuthority(x.getName()))
                .collect(Collectors.toList())
                .toString())){

            return modelAndView.addObject("userName",
                    new UserDto(SecurityContextHolder.getContext().getAuthentication().getName(),
                            userService.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName())
                                    .getRoles().stream()
                                    .map(x -> new SimpleGrantedAuthority(x.getName()))
                                    .collect(Collectors.toList())
                                    .toString()));

        }else{
            return modelAndView.addObject("userName",
                    new UserDto(
                            SecurityContextHolder.getContext().getAuthentication().getName(),
                            userService.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName())
                                    .getRoles().stream()
                                    .map(x -> new SimpleGrantedAuthority(x.getName()))
                                    .collect(Collectors.toList())
                                    .toString()));
        }
    }
}
