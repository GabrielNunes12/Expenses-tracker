package com.devnaut.expensetracker.expensive.controllers;

import com.devnaut.expensetracker.expensive.dtos.ExpensesDTO;
import com.devnaut.expensetracker.expensive.services.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/api/v1/expenses")
public class ExpensesController {
  private final ExpenseService expenseService;

  public ExpensesController(ExpenseService expenseService){
    this.expenseService = expenseService;
  }

  @GetMapping("/{userId}")
  public ResponseEntity<List<ExpensesDTO>> getAllExpenses(@PathVariable Long userId) {
    return ResponseEntity.ok(expenseService.findAllExpenses(userId));
  }
  @PostMapping
  public ResponseEntity<String> addExpanses(@RequestBody ExpensesDTO expensesDTO) {
    expenseService.addExpenses(expensesDTO);
    return ResponseEntity.ok("Expense created successfully");
  }
  @PostMapping("/editExpense/{id}")
  public ResponseEntity<String> updateExpanse(@PathVariable Long id, @RequestBody ExpensesDTO expensesDTO) {
    return ResponseEntity.ok(expenseService.updateExpanse(id, expensesDTO));
  }

  @DeleteMapping
  public ResponseEntity<?> deleteExpense(@PathVariable Long id) {
    return ResponseEntity.ok().body(expenseService.removeExpense(id) == 0 ? HttpStatus.NOT_FOUND : HttpStatus.ACCEPTED);
  }

}
