package com.example.task5.controller;

import com.example.task5.dto.UserDto;
import com.example.task5.repository.UserRepository;
import com.example.task5.service.CollectionService;
import com.example.task5.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.stream.Collectors;


@RequestMapping
@RestController
public class MainController {

    private CollectionService collectionService;

    public MainController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping(value = "/")
    public ModelAndView mainPage(HttpServletRequest  request){
        ModelAndView modelAndView = new ModelAndView("main_page");
        modelAndView.addObject("collectionImg", collectionService.getCollectionImg());
        if(Objects.equals(SecurityContextHolder.getContext().getAuthentication().getName(), "anonymousUser")){
            return modelAndView.addObject("anonymousUser", "anonymousUser");

        }else if(request.isUserInRole("ROLE_ADMIN")){
            return modelAndView.addObject("userName", new UserDto(
                    SecurityContextHolder.getContext().getAuthentication().getName(),
                    "ROLE_ADMIN"
            ));
        }else{
            return modelAndView.addObject("userName", new UserDto(
                    SecurityContextHolder.getContext().getAuthentication().getName(),
                    "ROLE_USER"
            ));
        }
    }
}
