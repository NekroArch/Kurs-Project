package com.example.admin.service.impl;

import com.example.admin.dto.UserDto;
import com.example.admin.repository.RoleRepository;
import com.example.admin.repository.UserRepository;
import com.example.admin.service.UserService;
import com.example.entities.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.websocket.server.ServerEndpoint;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@Service
public class UserServiceImpl implements UserService , UserDetailsService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(x-> new UserDto(x.getName(), x.getMail(), x.getPassword(), x.getStatus() , x.getRoles().stream().map(z->z.getName()).collect(joining(",")))).collect(toList());
    }

    @Transactional
    @Override
    public void deleteUser(String name) {
        userRepository.deleteUser(name);
    }

    @Transactional
    @Override
    public void changeStatusByName(String name, Boolean status) {

        userRepository.changeStatusByName(name,!status);
    }


    @Transactional
    @Override
    public void setRole(String name, int roleId){
        roleRepository.setRole(
                userRepository.findUserIdByName(name),
                roleId
        );
    }

    @Transactional
    @Override
    public void changeRoleByName(String name, int roleId) {
        roleRepository.changeStatus(
                userRepository.findUserIdByName(name),
                roleId
        );
    }

    @Override
    public int findUserIdByName(String name) {
        return userRepository.findUserIdByName(name);
    }

    @Override
    public User findUserByName(String name) {
        User userFoundByName = userRepository.findUserByName(name);
        return userFoundByName;
    }
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User userFoundByName = userRepository.findUserByName(name);

        if(userFoundByName.getStatus()) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(userFoundByName.getName())
                    .password(userFoundByName.getPassword())
                    .authorities(userFoundByName.getRoles().stream()
                            .map(x -> new SimpleGrantedAuthority(x.getName()))
                            .collect(toList()))
                    .build();
        }else{
            throw new UsernameNotFoundException("");
        }
    }

    }
