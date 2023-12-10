package com.devnaut.expensetracker.expensive.models;

import com.devnaut.expensetracker.expensive.enums.UserEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
  @Column(unique = true)
  private String username;
  @CreationTimestamp
  private LocalDateTime createdAt;
  @CreationTimestamp
  private LocalDateTime updatedAt;
  @OneToOne
  private Income income;
  @OneToMany
  private List<Expenses> expensesList = new ArrayList<>();
}
