package com.devnaut.expensetracker.expensive.repositories;

import com.devnaut.expensetracker.expensive.models.Expenses;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class ExpensesRepositoryTest {

  @Autowired
  private ExpenseRepository expenseRepository;
  private Long existingId;
  private Long nonExistingID;

  @BeforeEach
  void setup() {
    existingId = 1L;
    nonExistingID = 1000L;
  }

  @Test
  public void expenseShouldDeleteById() {
    expenseRepository.deleteById(existingId);
    Optional<Expenses> expensesOptional = expenseRepository.findById(existingId);
    Assertions.assertTrue(expensesOptional.isEmpty());
  }
  @Test
  public void findByIdShouldBeNullOrInexistent() {
    Optional<Expenses> expensesOptional = expenseRepository.findById(nonExistingID);
    Assertions.assertTrue(expensesOptional.isEmpty());
  }
}
