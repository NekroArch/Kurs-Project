package com.example.common.controller;

import com.example.common.dto.CollectionDto;
import com.example.common.dto.CollectionImgDto;
import com.example.common.dto.UserDto;
import com.example.common.service.CollectionService;
import com.example.common.service.KafkaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Api(description = "Controller for main page")
@RequestMapping
@RestController
public class MainController {

    private CollectionService collectionService;
    private KafkaService kafkaService;

    public MainController(CollectionService collectionService, KafkaService kafkaService) {
        this.collectionService = collectionService;
        this.kafkaService = kafkaService;
    }

    @ApiOperation(value = "Method to load main page")
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

    @GetMapping(value = "/collectionSearch")
    public ModelAndView collectionSearch(@RequestParam("collectionName") String collectionName, HttpServletRequest request){

        List<CollectionDto> collectionImgDtoList = collectionService.findCollectionByName(collectionName);

        if(Objects.equals(collectionImgDtoList.size(), 0)){
            ModelAndView modelAndView = new ModelAndView();
            return modelAndView.addObject(HttpStatus.BAD_REQUEST);
        }else{
            ModelAndView modelAndView = new ModelAndView("search_page");

            if(Objects.equals(SecurityContextHolder.getContext().getAuthentication().getName(), "anonymousUser")){
                modelAndView.addObject("anonymousUser", "anonymousUser");

            }else if(request.isUserInRole("ROLE_ADMIN")){
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
            modelAndView.addObject("collectionDto", collectionImgDtoList);
            return modelAndView.addObject(HttpStatus.OK);
        }
    }

    @ApiIgnore
    @GetMapping(value = "/admin")
    public void postKafkaMessage(){
        RequestContextHolder.currentRequestAttributes().getSessionId();
        SecurityContextHolder.getContext().getAuthentication().getCredentials();
        kafkaService.sendMessage(SecurityContextHolder.getContext().getAuthentication().getName());
    }

}
