package appSoft.project.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appSoft.project.model.Expense;
import appSoft.project.repository.ExpenseRepository;
import appSoft.project.service.ExpenseService;


@Service
public class ExpenseServiceImpl implements ExpenseService {
	@Autowired
	ExpenseRepository expenseRepository;
		@Override
		public void addExpense(Expense expense) {
			expenseRepository.save(expense);
		}

		@Override
		public List<Expense> getAllExpense() {
			return expenseRepository.findAll();
		}

		@Override
		public void deleteExpenseById(int id) {
			expenseRepository.deleteById(id);
		}

		@Override
		public Expense getExpenseById(int id) {
			return expenseRepository.findById(id).get();
		}

		@Override
		public void updateExpense(Expense expense) {
			expenseRepository.save(expense);
		}

		@Override
		public List<Expense> getAllByPurchaseDateBetween(LocalDate expenseFrom, LocalDate expenseTo) {
			return expenseRepository.findAllByPurchaseDateBetween(expenseFrom, expenseTo);
		}

		@Override
		public List<Expense> getAllByPurchaseDateBetweenAndGrade(LocalDate expenseFrom, LocalDate expenseTo,
				String grade) {
			return expenseRepository.findAllByPurchaseDateBetweenAndGrade(expenseFrom, expenseTo, grade);
		}

}
