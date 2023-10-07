package com.devnaut.expensetracker.expensive.dtos;

import com.devnaut.expensetracker.expensive.enums.UserEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
  private String username;
  private String password;
  private String email;
  private UserEnum userEnum;
}
