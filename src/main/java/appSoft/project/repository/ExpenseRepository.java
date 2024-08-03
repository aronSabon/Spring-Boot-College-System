package appSoft.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import appSoft.project.model.Exam;
import appSoft.project.model.Expense;
import appSoft.project.model.Student;
import jakarta.transaction.Transactional;

import java.time.LocalDate;


public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
List<Expense> findByPurchaseDate(LocalDate purchaseDate);

List<Expense> findAllByPurchaseDateBetween(LocalDate expenseFrom, LocalDate expenseTo);
}
