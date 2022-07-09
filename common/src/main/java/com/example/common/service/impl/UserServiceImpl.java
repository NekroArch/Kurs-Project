package com.example.common.service.impl;

import com.example.common.dto.UserDto;
import com.example.common.repository.RoleRepository;
import com.example.common.repository.UserRepository;
import com.example.entities.User;
import com.example.common.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
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
    public void createUser(String name, String password, String email, Boolean turn) {
        userRepository.createUser(
                name,
                passwordEncoder.encode(password),
                email,
                turn,
                Base64.getEncoder().encodeToString(password.getBytes()));

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
