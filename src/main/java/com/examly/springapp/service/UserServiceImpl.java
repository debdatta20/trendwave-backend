
package com.examly.springapp.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.examly.springapp.config.JwtUtils;
import com.examly.springapp.exceptions.UnauthorizeException;
import com.examly.springapp.model.LoginDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

   
    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    @Override
    public User createUser(User user){
         if (userRepo.existsByEmail(user.getEmail())) {
             throw new UnauthorizeException("User with email " + user.getEmail() + " already Exists");
         }
         user.setPassword(passwordEncoder.encode(user.getPassword()));
         return userRepo.save(user);
    }

    @Override
    public String loginUser(LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword()));
        if(authentication.isAuthenticated()){
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return jwtUtils.generateToken(userDetails);
        }
        else{
            throw new UnauthorizeException("Invalid Email and Password");
        }
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

}

