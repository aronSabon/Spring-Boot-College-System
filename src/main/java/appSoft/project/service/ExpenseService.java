package appSoft.project.service;

import java.time.LocalDate;
import java.util.List;

import appSoft.project.model.Expense;

public interface ExpenseService {
		void addExpense(Expense expense);
		List<Expense> getAllExpense();
		void deleteExpenseById(int id);
		Expense getExpenseById(int id);
		void updateExpense(Expense expense);
		List<Expense> getAllByPurchaseDateBetween(LocalDate expenseFrom, LocalDate expenseTo);
		
		List<Expense> getAllByPurchaseDateBetweenAndGrade(LocalDate expenseFrom, LocalDate expenseTo, String grade);

}
