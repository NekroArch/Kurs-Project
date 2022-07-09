package com.example.admin.controller;

import com.example.admin.dto.RoleDto;
import com.example.admin.dto.StatusDto;

import com.example.admin.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Objects;

@RequestMapping(value = "/admin")
@RestController
public class AdminController {

    private final UserService userService;
    private final AuthenticationManager aunt;

    String userName;

    public AdminController(UserService userService, AuthenticationManager aunt) {
        this.userService = userService;
        this.aunt = aunt;
    }


    @GetMapping()
    public ModelAndView getUsers() {
        ModelAndView modelAndView = new ModelAndView("admin_page");
        modelAndView.addObject("userName",SecurityContextHolder.getContext().getAuthentication().getName());
        return modelAndView.addObject("userDto", userService.getAllUsers());
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity deleteUser(@RequestBody ArrayList<String> loginDto) {
        if (Objects.equals(loginDto.size(), 0)){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }else{
            loginDto.forEach(x -> userService.deleteUser(x));
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @PatchMapping(value = "/status")
    public ResponseEntity changeStatus(@RequestBody ArrayList<StatusDto> statusDto) {
        if (Objects.equals(statusDto.size(), 0)){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }else{
            statusDto.forEach(x -> userService.changeStatusByName(x.getName(), x.getStatus()));
            return new ResponseEntity(HttpStatus.OK);
        }

    }


    @PatchMapping(value = "/role")
    public ResponseEntity changeRole(@RequestBody ArrayList<RoleDto> roleDto) {
        if (Objects.equals(roleDto.size(), 0)){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }else{
            roleDto.forEach(x -> userService.changeRoleByName(x.getName(), x.getRoleId()));
            return new ResponseEntity(HttpStatus.OK);
        }
    }

//    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
//    public void consumeMessage(String message){
//        userName = message;
//
//        UsernamePasswordAuthenticationToken authReq
//                = new UsernamePasswordAuthenticationToken(message, 12345);
//        Authentication auth = aunt.authenticate(authReq);
//        SecurityContext sc = SecurityContextHolder.getContext();
//        sc.setAuthentication(auth);// Convert to jwt token // Convert to jwt token
//        UsernamePasswordAuthenticationToken authReq
//                = new UsernamePasswordAuthenticationToken(user, pass);
//        Authentication auth = authManager.authenticate(authReq);
//
//        SecurityContext sc = SecurityContextHolder.getContext();
//        sc.setAuthentication(auth);
//        HttpSession session = req.getSession(true);
//        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
//    }
}
