package com.lcobzaru.bootcamp.socialmediaapp.controller;

import com.lcobzaru.bootcamp.socialmediaapp.dto.UserDTO;
import com.lcobzaru.bootcamp.socialmediaapp.models.User;
import com.lcobzaru.bootcamp.socialmediaapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.Optional;

@RestController

public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping("/api")
    public String hello(){
        return "Home page!";
    }

//
    @PostMapping("/api/users")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO){
        // TODO Validate fields like sex email etc
//        ValidationUtils.validateEmail(userDTO);

        User user = modelMapper.map(userDTO, User.class);
        UserDTO savedUser = userService.registerNewUserAccount(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(savedUser, UserDTO.class));
    }

    @DeleteMapping("/api/{id}")
    public ResponseEntity delete (@PathVariable("id") Long id){
        User foundUser = userService.getById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        userService.delete(foundUser);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/{id}")
    public ResponseEntity<UserDTO> getUserProfile (@PathVariable("id") Long id, Principal principal){
        User user = userService.getById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)); //return id from request

        String principalUsername = principal.getName();
        User user1 = userService.getByUsername(principalUsername).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)); //return id from db for principal username

        if(user.getId() == user1.getId()){
            UserDTO returnedUser = userService.getUserProfileForMyUser(user1);
            return ResponseEntity.status(HttpStatus.FOUND).body(modelMapper.map(returnedUser, UserDTO.class));
        }
        UserDTO returnedUser = userService.getUserProfileForAnotherUser(user1);
        return ResponseEntity.status(HttpStatus.FOUND).body(modelMapper.map(returnedUser, UserDTO.class));
    }

    @PutMapping("/api/{id}")
    public ResponseEntity<UserDTO> updateUserProfile (@PathVariable("id") Long id, Principal principal){
        User user = userService.getById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        String principalUsername = principal.getName();
        User user1 = userService.getByUsername(principalUsername).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(user.getId() != user1.getId()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        UserDTO returnedUser = userService.updateUserProfile(user1);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(modelMapper.map(returnedUser, UserDTO.class));
    }


}


