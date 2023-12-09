package com.devnaut.expensetracker.expensive.dtos;

import com.devnaut.expensetracker.expensive.utils.Income;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
public class IncomeDTO implements Income {
  private Long id;
  private BigDecimal incomeAmount;
  private String description;

  public IncomeDTO() {
  }

  public IncomeDTO(com.devnaut.expensetracker.expensive.models.Income income) {
    this.id = income.getId();
    this.description = income.getDescription();
    this.incomeAmount = income.getIncomeAmount();
  }

  @Override
  public Long id() {
    return this.id;
  }

  @Override
  public BigDecimal incomeAmount() {
    return this.incomeAmount;
  }

  @Override
  public String description() {
    return this.description;
  }
}
