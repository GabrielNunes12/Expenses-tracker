package com.devnaut.expensetracker.expensive.services;

import com.devnaut.expensetracker.expensive.config.AppCache;
import com.devnaut.expensetracker.expensive.dtos.ExpensesDTO;
import com.devnaut.expensetracker.expensive.enums.CategoryEnum;
import com.devnaut.expensetracker.expensive.models.Expenses;
import com.devnaut.expensetracker.expensive.repositories.ExpenseRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

  private final ExpenseRepository expenseRepository;
  private final AppCache appCache;

  public ExpenseService(ExpenseRepository expenseRepository, AppCache appCache) {
    this.expenseRepository = expenseRepository;
    this.appCache = appCache;
  }
  public List<ExpensesDTO> findAllExpenses(Long userId) {
    List<ExpensesDTO> expensesDTOS = new ArrayList<>();
    List<Expenses> expensesFound = expenseRepository.findByUserId(userId);
    expensesFound.forEach(expenses -> {
      ExpensesDTO expenseDTO = new ExpensesDTO();
      expenseDTO.setDescription(expenses.getDescription());
      expenseDTO.setValue(expenses.getValue());
      expenseDTO.setId(expenses.getId());
      expenseDTO.setType(expenses.getCategoryEnum().getName());
      expensesDTOS.add(expenseDTO);
    });
    appCache.cacheManager().getCache("expenses").put("expenses", expensesDTOS);
    return expensesDTOS;
  }
  @Transactional
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

  @Transactional
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
  /**
  * @author Gabriel Nunes
  * @return Se for encontrado a expense, então retorna 1, caso contrário retorna 0
  * */
  @SneakyThrows
  @Transactional
  public int removeExpense(Long expenseId) {
    //achou expense
    Optional<Expenses> foundExpense = expenseRepository.findById(expenseId);
    if(foundExpense.isPresent()) {
      //remove
      expenseRepository.delete(foundExpense.get());
      return 1;
    }
    return 0;
  }
}
