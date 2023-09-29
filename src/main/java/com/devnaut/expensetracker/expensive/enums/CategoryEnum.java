package com.devnaut.expensetracker.expensive.enums;

public enum CategoryEnum {
  GROCERIES("GROCERIES", "Groceries"),
  TRANSPORTATION("TRANSPORTATION", "Transportation"),
  DINNING("DINNING", "Dinning");
  String name;
  String code;
  CategoryEnum(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public String getCode() {
    return code;
  }
}
