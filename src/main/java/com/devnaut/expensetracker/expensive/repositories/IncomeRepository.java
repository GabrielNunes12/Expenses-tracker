package com.devnaut.expensetracker.expensive.repositories;

import com.devnaut.expensetracker.expensive.models.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
  interface IncomeUser {
    Long id();
    BigDecimal incomeAmount();
    Long userId();
    String description();
  }
  @Query(value = "SELECT * FROM income WHERE user_id = ?1", nativeQuery = true)
  IncomeUser findByUserId(Long id);
}
