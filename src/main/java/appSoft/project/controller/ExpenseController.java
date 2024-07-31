package appSoft.project.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import appSoft.project.model.Expense;
import appSoft.project.model.ExpenseReport;
import appSoft.project.repository.ExpenseRepository;
import appSoft.project.service.ExpenseService;


@Controller
public class ExpenseController {
	@Autowired
	ExpenseService expenseService;
	@Autowired
	ExpenseRepository expenseRepository;
	
	@GetMapping("/addExpense")
	private String expenseForm(Model model) {
		model.addAttribute("date",LocalDate.now());
		return "AddExpense";
	}
	@PostMapping("/addExpense")
	private String addExpense(@ModelAttribute Expense expense) {
		expenseService.addExpense(expense);
		return "redirect:/addExpense";
	}
	
	@GetMapping("/expenseList")
	private String expenseList(Model model) {
		model.addAttribute("expenseList", expenseService.getAllExpense());
		return "ExpenseList";
	}
	@GetMapping("/deleteExpense")
	private String deleteExpense(@RequestParam int id) {
		expenseService.deleteExpenseById(id);
		return "redirect:/expenseList";
	}
	@GetMapping("/editExpense")
	private String editExpense(@RequestParam int id,Model model) {
		model.addAttribute("expenseModel",expenseService.getExpenseById(id));
		return "EditExpense";
	}
	@PostMapping("/updateExpense")
	private String updateExpense(@ModelAttribute Expense expense) {
		expenseService.updateExpense(expense);
		return "redirect:/expenseList";
	}
	@GetMapping("/expenseReport")
	private String expenseReport() {
		return "ExpenseReport";
	}
	@PostMapping("/expenseReport")
	private String post(@ModelAttribute ExpenseReport expenseReport,Model model) {
		List<Expense>expenseList=expenseRepository.findAllByPurchaseDateBetween(expenseReport.getExpenseFrom(), expenseReport.getExpenseTo());
		model.addAttribute("expenseFrom",expenseReport.getExpenseFrom());
		model.addAttribute("expenseTo",expenseReport.getExpenseTo());

		model.addAttribute("expenseList",expenseRepository.findAllByPurchaseDateBetween(expenseReport.getExpenseFrom(), expenseReport.getExpenseTo()));
		return "ExpenseReportDetail";
	}
	
}
