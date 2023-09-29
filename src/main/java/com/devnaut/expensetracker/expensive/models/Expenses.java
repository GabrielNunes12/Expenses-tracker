package com.devnaut.expensetracker.expensive.models;

import com.devnaut.expensetracker.expensive.enums.CategoryEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
public class Expenses {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String description;
  @Enumerated(EnumType.STRING)
  private CategoryEnum categoryEnum;
  private BigDecimal value;
}
