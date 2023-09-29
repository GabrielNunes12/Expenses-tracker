package com.devnaut.expensetracker.expensive.repositories;

import com.devnaut.expensetracker.expensive.models.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expenses, Long> {
}
