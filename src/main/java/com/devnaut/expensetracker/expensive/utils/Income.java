package com.devnaut.expensetracker.expensive.utils;

import java.math.BigDecimal;

public interface Income {
  Long id();
  BigDecimal incomeAmount();
  String description();
}
