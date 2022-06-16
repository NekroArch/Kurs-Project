package com.example.task5.controller;

import com.example.task5.dto.CreateCollectionDto;
import com.example.task5.dto.CreateItemDto;
import com.example.task5.dto.UserDto;
import com.example.task5.service.CollectionService;
import com.example.task5.service.ItemService;
import com.example.task5.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RequestMapping(value = "/profile")
@RestController
public class ProfileController {

    private CollectionService collectionService;
    private ItemService itemService;
    private UserService userService;


    public ProfileController(CollectionService collectionService, ItemService itemService, UserService userService) {
        this.collectionService = collectionService;
        this.itemService = itemService;
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public ModelAndView profilePage(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("profile_page");

        if(request.isUserInRole("ROLE_ADMIN")){
            modelAndView.addObject("userName", new UserDto(
                    SecurityContextHolder.getContext().getAuthentication().getName(),
                    "ROLE_ADMIN"
            ));
        }else{
            modelAndView.addObject("userName", new UserDto(
                    SecurityContextHolder.getContext().getAuthentication().getName(),
                    "ROLE_USER"
            ));
        }

        return modelAndView.addObject("collectionDto", collectionService.getCollectionByUserId(
                userService.findUserIdByName(SecurityContextHolder.getContext().getAuthentication().getName())
        ));
    }

    @GetMapping(value = "/userCollection")
    public ModelAndView profilePage(@RequestParam("name") String name, HttpServletRequest request){
        name = name.replace("\"","");
        ModelAndView modelAndView = new ModelAndView("profile_page");


        if(request.isUserInRole("ROLE_ADMIN")){
            modelAndView.addObject("userName", new UserDto(
                    SecurityContextHolder.getContext().getAuthentication().getName(),
                    "ROLE_ADMIN"
            ));
        }

        return modelAndView.addObject("collectionDto", collectionService.getCollectionByUserId(
                userService.findUserIdByName(name)
        ));
    }

    @PostMapping(value = "/createCollection")
    public void createCollection(@RequestBody CreateCollectionDto createCollectionDto){
        if(!Objects.equals(createCollectionDto.getName(), "") && !Objects.equals(createCollectionDto.getTopic(), "") &&
           !Objects.equals(createCollectionDto.getDescription(), "")){
        collectionService.save(createCollectionDto);
        }
    }

    @PostMapping(value = "/createItem")
    public void createItem(@RequestBody ArrayList<CreateItemDto> createItemDto){
        createItemDto.stream()
                .filter(x -> !Objects.equals(x.getName(), "") && !Objects.equals(x.getTagName(), ""))
                .forEach(x -> itemService.createItem(x.getName(), x.getCollectionId(), x.getTagName(), x.getItemAttributeNameDto(), x.getItemAttributeValueDto()));
    }

    @DeleteMapping(value = "/deleteCollection")
    public void deleteCollection(@RequestBody ArrayList<Integer> id){
        if(!Objects.equals(id, null)){
            id.forEach(x -> collectionService.deleteCollection(x));
        }
    }
}
