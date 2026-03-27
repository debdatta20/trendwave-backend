
package com.examly.springapp.service;


import com.examly.springapp.model.LoginDTO;
import com.examly.springapp.model.User;

public interface UserService {

    User createUser(User user);
    String loginUser(LoginDTO loginDTO);
    User getUserByEmail(String email);
}

