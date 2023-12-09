package com.devnaut.expensetracker.expensive.services;

import com.devnaut.expensetracker.expensive.dtos.IncomeDTO;
import com.devnaut.expensetracker.expensive.models.Income;
import com.devnaut.expensetracker.expensive.repositories.IncomeRepository;
import com.devnaut.expensetracker.expensive.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class IncomeService {
  private final IncomeRepository incomeRepository;
  private final UserRepository userRepository;
  public IncomeService(IncomeRepository income, UserRepository userRepository) {
    this.incomeRepository = income;
    this.userRepository = userRepository;
  }

  public void addIncome(com.devnaut.expensetracker.expensive.utils.Income income) {
    Income newIncome = new Income();
    newIncome.setIncomeAmount(income.incomeAmount());
    newIncome.setDescription(income.description());
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

  public IncomeDTO getExactIncomeFromUser(Long id) {
    IncomeRepository.IncomeUser userIncome = incomeRepository.findByUserId(id);
    return new IncomeDTO((Income) userIncome);
  }
}
