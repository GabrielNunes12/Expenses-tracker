package com.devnaut.expensetracker.expensive.models;

import com.devnaut.expensetracker.expensive.enums.UserEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "user_authority")
  @Enumerated(EnumType.STRING)
  private UserEnum authority;
  private String password;
  private String username;
}
