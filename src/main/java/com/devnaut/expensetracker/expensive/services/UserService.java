package com.devnaut.expensetracker.expensive.services;

import com.devnaut.expensetracker.expensive.dtos.UserDTO;
import com.devnaut.expensetracker.expensive.models.User;
import com.devnaut.expensetracker.expensive.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public Map isUserLoggedIn(UserDTO userCredentials) {
    Map user = new HashMap<>();
    user.put("username", userCredentials.getUsername());
    user.put("alreadyCreated", userRepository.findByLogin(userCredentials.getUsername()));
    return user;
  }

  public String registerUser(UserDTO user) {
    if(userRepository.findByLogin(user.getUsername())) return "User created already!";
    User newUser = new User();
    newUser.setAuthority(user.getUserEnum());
    newUser.setPassword(user.getPassword());
    newUser.setUsername(user.getUsername());
    userRepository.save(newUser);
    return "User created";
  }
}
