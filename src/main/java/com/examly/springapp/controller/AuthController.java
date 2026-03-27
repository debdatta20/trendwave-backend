
package com.examly.springapp.controller;

import java.util.Map;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.examly.springapp.model.LoginDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api")
public class AuthController {

    

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user){
       User createdUser = userService.createUser(user);
       if(createdUser != null){
        return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
       }else{
        return new  ResponseEntity<>(HttpStatus.CONFLICT);
       }
    }


    
@PostMapping("/login")
public ResponseEntity<?> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
    String token = userService.loginUser(loginDTO);
    User user = userService.getUserByEmail(loginDTO.getEmail()); // Add this method in UserService

    if (token != null && user != null) {
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("userRole", user.getUserRole()); // Assuming getRole() returns "ADMIN" or "USER"
        response.put("userId", user.getUserId());

        return new ResponseEntity<>(response, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

}


