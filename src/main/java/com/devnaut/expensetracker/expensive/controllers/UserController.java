package com.devnaut.expensetracker.expensive.controllers;

import com.devnaut.expensetracker.expensive.dtos.UserDTO;
import com.devnaut.expensetracker.expensive.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
@RequestMapping(value = "/api/v1/users")
@AllArgsConstructor
public class UserController {
  private final UserService userService;
  @PostMapping
  public ResponseEntity<Map> isClientLoggedIn(@RequestBody UserDTO clientCredentials) {
    return ResponseEntity.ok().body(userService.isUserLoggedIn(clientCredentials));
  }
  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody UserDTO user) {
    return ResponseEntity.ok().body(userService.registerUser(user));
  }
}
