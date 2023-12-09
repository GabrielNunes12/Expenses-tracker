package com.devnaut.expensetracker.expensive.controllers;

import com.devnaut.expensetracker.expensive.dtos.IncomeDTO;
import com.devnaut.expensetracker.expensive.services.IncomeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/api/v1/incomes")
public class IncomesController {

  private final IncomeService incomeService;

  public IncomesController(IncomeService incomeService){
    this.incomeService = incomeService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getExactIncomeFromUser(@PathVariable Long id) {
    return ResponseEntity.ok(incomeService.getExactIncomeFromUser(id));
  }
  @PostMapping
  public ResponseEntity<String> addIncome(@RequestBody IncomeDTO incomeDTO) {
    incomeService.addIncome(incomeDTO);
    return ResponseEntity.ok("Expense created successfully");
  }

}
