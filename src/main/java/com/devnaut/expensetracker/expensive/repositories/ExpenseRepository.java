package com.devnaut.expensetracker.expensive.repositories;

import com.devnaut.expensetracker.expensive.models.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expenses, Long> {
  @Query(value = "SELECT * FROM expenses WHERE user_id = ?1", nativeQuery = true)
  List<Expenses> findByUserId(Long id);
  void deleteById(Long id);
}
