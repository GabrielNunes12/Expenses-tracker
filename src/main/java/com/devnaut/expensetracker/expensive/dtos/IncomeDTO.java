package com.devnaut.expensetracker.expensive.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
public class IncomeDTO {
  private Long id;
  private BigDecimal incomeAmount;
  private String description;
}
