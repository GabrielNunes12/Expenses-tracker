package com.devnaut.expensetracker.expensive.enums;

public enum UserEnum {
  ADMIN("ADMIN", "Admin"),
  COMMON_USER("COMMON_USER", "Common User");
  String code, description;
  UserEnum(String code, String description) {
    this.code = code;
    this.description = description;
  }
}
