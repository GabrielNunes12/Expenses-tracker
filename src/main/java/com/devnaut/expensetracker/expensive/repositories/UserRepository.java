package com.devnaut.expensetracker.expensive.repositories;

import com.devnaut.expensetracker.expensive.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  @Query(value = "SELECT CASE WHEN EXISTS (SELECT 1 FROM users u WHERE u.username = ?1) THEN true ELSE false END", nativeQuery = true)
  boolean findByLogin(String userLogin);
}
