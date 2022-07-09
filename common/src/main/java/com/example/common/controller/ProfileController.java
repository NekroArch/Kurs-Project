package com.example.common.controller;

import com.example.common.dto.create.CreateCollectionDto;
import com.example.common.dto.create.CreateItemDto;
import com.example.common.dto.UserDto;
import com.example.common.service.CollectionService;
import com.example.common.service.ItemService;
import com.example.common.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Objects;

@Api(description = "Controller for profile page")
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

    @ApiOperation(value = "Method to load profile page")
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

    @ApiOperation(value = "Method to load profile page by user name (works only for authorized administrator)")
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


    @ApiOperation(value = "Method to create collection")
    @PostMapping(value = "/createCollection")
    public ResponseEntity createCollection(@RequestBody CreateCollectionDto createCollectionDto){
        if (!Objects.equals(createCollectionDto.getName(), "") && !Objects.equals(createCollectionDto.getDescription(), "") &&
                !Objects.equals(createCollectionDto.getTopic(), "")) {

            collectionService.save(createCollectionDto);
            return new ResponseEntity(HttpStatus.OK);

        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    @ApiOperation(value = "Method to created item for collection")
    @PostMapping(value = "/createItem")
    public ResponseEntity createItem(@RequestBody ArrayList<CreateItemDto> createItemDto){
        if(!Objects.equals(createItemDto.get(0).getName(), "") && !Objects.equals(createItemDto.get(0).getTagName(), "")){
            createItemDto.forEach(x -> itemService.createItem(x.getName(), x.getCollectionId(), x.getTagName(), x.getItemAttributeNameDto(), x.getItemAttributeValueDto()));
            return new ResponseEntity(HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    @ApiOperation(value = "Method to delete collection")
    @DeleteMapping(value = "/deleteCollection")
    public ResponseEntity deleteCollection(@RequestBody ArrayList<Integer> id){
        if (Objects.equals(id.size(), 0)){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }else{
            id.forEach(x -> collectionService.deleteCollection(x));
            return new ResponseEntity(HttpStatus.OK);
        }
    }


}
