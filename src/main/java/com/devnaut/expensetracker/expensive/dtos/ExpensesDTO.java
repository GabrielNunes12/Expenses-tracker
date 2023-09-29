package com.devnaut.expensetracker.expensive.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
public class ExpensesDTO {
  private Long id;
  private String type;
  private String description;
  private BigDecimal value;
}
