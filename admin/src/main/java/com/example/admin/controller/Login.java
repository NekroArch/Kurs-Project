package com.example.admin.controller;

import com.example.admin.dto.RoleDto;
import com.example.admin.dto.StatusDto;

import com.example.admin.dto.UserDto;
import com.example.admin.service.UserService;
import com.example.entities.User;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Objects;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@RequestMapping(value = "/login")
@RestController
public class Login {

    private final UserService userService;
    private final AuthenticationManager aunt;

    String userName;

    public Login(UserService userService, AuthenticationManager aunt) {
        this.userService = userService;
        this.aunt = aunt;
    }

    @GetMapping()
    public ModelAndView getUsers() {
        User user = userService.findUserByName(userName);

        UsernamePasswordAuthenticationToken authReq
                = new UsernamePasswordAuthenticationToken(userName, new String(Base64.getDecoder().decode(user.getHpassword())));
        Authentication auth = aunt.authenticate(authReq);

        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);

        userName = null;
        return new ModelAndView("redirect:/admin");

    }


    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeMessage(String message){
        userName = message;
    }
}
