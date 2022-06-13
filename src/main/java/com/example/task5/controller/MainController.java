package com.example.task5.controller;

import com.example.task5.dto.CollectionDto;
import com.example.task5.dto.UserDto;
import com.example.task5.repository.CollectionRepository;
import com.example.task5.service.CollectionService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;


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

    @GetMapping(value = "/getColl")
    public List<CollectionDto> createItem(){
        return collectionService.getCollectionByUserId(1);
    }

}
