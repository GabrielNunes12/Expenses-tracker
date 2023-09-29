package com.devnaut.expensetracker.expensive.services;

import com.devnaut.expensetracker.expensive.dtos.ExpensesDTO;
import com.devnaut.expensetracker.expensive.enums.CategoryEnum;
import com.devnaut.expensetracker.expensive.models.Expenses;
import com.devnaut.expensetracker.expensive.repositories.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

  private final ExpenseRepository expenseRepository;

  public ExpenseService(ExpenseRepository expenseRepository) {
    this.expenseRepository = expenseRepository;
  }
  public List<ExpensesDTO> findAllExpenses() {
    List<ExpensesDTO> expensesDTOS = new ArrayList<>();
    ExpensesDTO expensesDTO = new ExpensesDTO();
    expenseRepository.findAll().forEach(expenses -> {
      expensesDTO.setDescription(expenses.getDescription());
      expensesDTO.setValue(expenses.getValue());
      expensesDTO.setId(expenses.getId());
      expensesDTO.setType(expenses.getCategoryEnum().getName());
      expensesDTOS.add(expensesDTO);
    });
    return expensesDTOS;
  }

  public void addExpenses(ExpensesDTO expensesDTO) {
    Expenses expenses = new Expenses();
    expenses.setDescription(expensesDTO.getDescription());
    expenses.setCategoryEnum(CategoryEnum.valueOf(expensesDTO.getType()));
    expenses.setValue(expensesDTO.getValue() == null || expensesDTO.getValue().intValue() < 0
            ? BigDecimal.valueOf(0)
            : expensesDTO.getValue()
    );
    expenseRepository.save(expenses);
  }

  public String updateExpanse(Long id, ExpensesDTO expensesDTO) {
    Optional<Expenses> expenses = expenseRepository.findById(id);
    if(expenses.isPresent()) {
      expenses.get().setCategoryEnum(CategoryEnum.valueOf(expensesDTO.getType()));
      expenses.get().setDescription(expensesDTO.getDescription());
      expenses.get().setValue(expensesDTO.getValue());
      return "Updated!";
    } else {
      return "ERROR";
    }
  }
}
