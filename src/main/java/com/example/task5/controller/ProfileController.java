package com.example.task5.controller;

import com.example.task5.dto.CreateCollectionDto;
import com.example.task5.dto.CreateItemDto;
import com.example.task5.dto.UserDto;
import com.example.task5.service.CollectionService;
import com.example.task5.service.ItemService;
import com.example.task5.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
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
    public ModelAndView profilePage(){
        ModelAndView modelAndView = new ModelAndView("profile_page");
        if("[ROLE_USER]".equals(userService.findUserByName(
                SecurityContextHolder.getContext().getAuthentication().getName())
                .getRoles().stream()
                .map(x -> new SimpleGrantedAuthority(x.getName()))
                .collect(toList())
                .toString())) {

            modelAndView.addObject("userName",
                    new UserDto(SecurityContextHolder.getContext().getAuthentication().getName(),
                            userService.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName())
                                    .getRoles().stream()
                                    .map(x -> new SimpleGrantedAuthority(x.getName()))
                                    .collect(toList())
                                    .toString()));
        }else{
            modelAndView.addObject("userName",
                    new UserDto(SecurityContextHolder.getContext().getAuthentication().getName(),
                            userService.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName())
                                    .getRoles().stream()
                                    .map(x -> new SimpleGrantedAuthority(x.getName()))
                                    .collect(toList())
                                    .toString()));
        }
        return modelAndView.addObject("collectionDto", collectionService.getCollectionByUserId(
                userService.findUserIdByName(SecurityContextHolder.getContext().getAuthentication().getName())
        ));
    }

    @GetMapping(value = "/userCollection")
    public ModelAndView profilePage(@RequestParam("name") String name){
        name = name.replace("\"","");
        ModelAndView modelAndView = new ModelAndView("profile_page");
        if("[ROLE_USER]".equals(userService.findUserByName(
                        SecurityContextHolder.getContext().getAuthentication().getName())
                .getRoles().stream()
                .map(x -> new SimpleGrantedAuthority(x.getName()))
                .collect(toList())
                .toString())) {

            modelAndView.addObject("userName",
                    new UserDto(SecurityContextHolder.getContext().getAuthentication().getName(),
                            userService.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName())
                                    .getRoles().stream()
                                    .map(x -> new SimpleGrantedAuthority(x.getName()))
                                    .collect(toList())
                                    .toString()));
        }else{
            modelAndView.addObject("userName",
                    new UserDto(SecurityContextHolder.getContext().getAuthentication().getName(),
                            userService.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName())
                                    .getRoles().stream()
                                    .map(x -> new SimpleGrantedAuthority(x.getName()))
                                    .collect(toList())
                                    .toString()));
        }
        return modelAndView.addObject("collectionDto", collectionService.getCollectionByUserId(
                userService.findUserIdByName(name)
        ));
    }

    @PostMapping(value = "/createCollection")
    public ModelAndView createCollection(@RequestBody CreateCollectionDto createCollectionDto){
        collectionService.save(createCollectionDto);
        return new ModelAndView("profile_page");
    }

    @PostMapping(value = "/createItem")
    public ModelAndView createItem(@RequestBody ArrayList<CreateItemDto> createItemDto){
        createItemDto.forEach(x -> itemService.createItem(x.getName(), x.getCollectionId(), x.getTagName(), x.getItemAttributeNameDto(), x.getItemAttributeValueDto()));
        return new ModelAndView("profile_page");
    }

    @DeleteMapping(value = "/delete")
    public ModelAndView deleteCollection(@RequestBody ArrayList<Integer> id){
        id.forEach(x -> collectionService.deleteCollection(x));
        return new ModelAndView("profile_page");
    }
}
