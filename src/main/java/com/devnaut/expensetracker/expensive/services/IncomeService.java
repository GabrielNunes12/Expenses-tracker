package com.devnaut.expensetracker.expensive.services;

import com.devnaut.expensetracker.expensive.dtos.IncomeDTO;
import com.devnaut.expensetracker.expensive.models.Income;
import com.devnaut.expensetracker.expensive.repositories.IncomeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class IncomeService {
  private IncomeRepository incomeRepository;
  public IncomeService(IncomeRepository income) {
    this.incomeRepository = income;
  }

  public void addIncome(Income income) {
    Income newIncome = new Income();
    newIncome.setIncomeAmount(income.getIncomeAmount());
    newIncome.setDescription(income.getDescription());
    incomeRepository.save(newIncome);
  }
  public IncomeDTO updateIncome(Income income) {
    //retrieve it from database
    Optional<Income> incomeOptional = incomeRepository.findById(income.getId());
    IncomeDTO incomeDTO = new IncomeDTO();
    Income incomeTransformed = null;
    if (incomeOptional.isPresent()) {
      incomeDTO.setDescription(incomeOptional.get().getDescription());
      incomeDTO.setId(incomeOptional.get().getId());
      incomeDTO.setIncomeAmount(incomeOptional.get().getIncomeAmount());
      incomeTransformed = transformDTOtoEntity(incomeDTO);
      incomeRepository.save(incomeTransformed);
      return incomeDTO;
    }
    incomeDTO.setIncomeAmount(BigDecimal.ZERO);
    incomeDTO.setDescription("");
    incomeDTO.setId(incomeOptional.get().getId());
    incomeTransformed = transformDTOtoEntity(incomeDTO);
    incomeRepository.save(incomeTransformed);
    return incomeDTO;
  }
  private Income transformDTOtoEntity(IncomeDTO incomeDTO) {
    Income income = new Income();
    income.setDescription(incomeDTO.getDescription());
    income.setId(incomeDTO.getId());
    income.setIncomeAmount(incomeDTO.getIncomeAmount());
    return income;
  }
}
